package cn.lanqiao.bankproject.service.impl;

import cn.lanqiao.bankproject.mappers.MoneyMapper;
import cn.lanqiao.bankproject.service.MoneyService;
import cn.lanqiao.bankproject.entity.dto.RechargeAdd;
import cn.lanqiao.bankproject.entity.dto.TranferAdd;
import cn.lanqiao.bankproject.entity.dto.Withdrawdel;
import cn.lanqiao.bankproject.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MoneyServiceimpl implements MoneyService {
    @Autowired
    private MoneyMapper moneyMapper;
    // @Override
    // public int Tranfertopeople(TranferAdd tranferAdd) {
    //     int result = moneyMapper.Tranfertopeople(tranferAdd);
    //         if(result>0){
    //             //转账成功
    //             return 1;
    //         }else {
    //             //转账失败
    //             return 0;
    //         }
    // }

    @Override
    public ResponseUtils<TranferAdd> Tranfertopeople(TranferAdd tranferAdd, String fromCard) {
        try {
            // 1. 验证账户是否存在
            if (moneyMapper.checkAccountExists(fromCard) == 0 ||
                    moneyMapper.checkAccountExists(tranferAdd.getBankcard()) == 0) {
                return new ResponseUtils<>(404, "账户不存在");
            }

            // 2. 检查余额是否充足
            Double balance = moneyMapper.getBalance(fromCard);
            if (balance == null || balance < tranferAdd.getMoney()) {
                return new ResponseUtils<>(404, "余额不足");
            }

            // 3. 扣除转出方余额
            int deductResult = moneyMapper.deductBalance(fromCard, tranferAdd.getMoney());
            if (deductResult <= 0) {
                throw new RuntimeException("扣款失败");
            }

            // 4. 增加接收方余额
            int increaseResult = moneyMapper.increaseBalance(tranferAdd.getBankcard(), tranferAdd.getMoney());
            if (increaseResult <= 0) {
                throw new RuntimeException("收款失败");
            }

            // 5. 记录转账记录
            // tranferAdd.setFromName("张三"); // 设置转出方姓名
            int recordResult = moneyMapper.Tranfertopeople(tranferAdd);
            if (recordResult <= 0) {
                throw new RuntimeException("记录转账失败");
            }
            return new ResponseUtils<>(200, "转账成功");
        } catch (Exception e) {
            log.error("转账失败", e);
            throw new RuntimeException("转账处理失败");
        }
    }

    @Override
    public int UpdateBalance(RechargeAdd rechargeAdd) {
        int result = moneyMapper.UpdateBalance(rechargeAdd);
        if(result>0){
            //充值成功
            return 1;
        }else {
            //充值失败
            return 0;
        }
    }

    @Override
    public int UpdateBalancedown(Withdrawdel withdrawdel) {
        // 先验证密码
        if (!verifyTradePwd(withdrawdel.getBank_card(), withdrawdel.getTrade_pwd())) {
            return -1; // 密码错误返回-1
        }
        int result = moneyMapper.UpdateBalancedown(withdrawdel);
        if (result > 0) {
            //提现成功
            return 1;
        } else {
            //提现失败
            return 0;
        }
    }

    @Override
    public String checkTradePwd(String bank_card) {
        return "";
    }

    @Override
    public boolean verifyTradePwd(String bank_card, String trade_pwd) {
        String dbPassword = moneyMapper.checkTradePwd(bank_card);
        return dbPassword != null && dbPassword.equals(trade_pwd);
    }

}
