package cn.lanqiao.bankproject.service.impl;

import cn.lanqiao.bankproject.entity.po.Users;
import cn.lanqiao.bankproject.mappers.MoneyMapper;
import cn.lanqiao.bankproject.mappers.UsersMapper;
import cn.lanqiao.bankproject.service.MoneyService;
import cn.lanqiao.bankproject.entity.dto.RechargeAdd;
import cn.lanqiao.bankproject.entity.dto.TranferAdd;
import cn.lanqiao.bankproject.entity.dto.Withdrawdel;
import cn.lanqiao.bankproject.utils.ResponseUtils;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
@Slf4j
public class MoneyServiceimpl implements MoneyService {
    @Autowired
    private MoneyMapper moneyMapper;
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public int Tranfertopeople(TranferAdd tranferAdd) {
        int result = moneyMapper.Tranfertopeople(tranferAdd);
        if (result > 0) {
            //转账成功
            return 1;
        } else {
            //转账失败
            return 0;
        }
    }


    // @Override
    // public ResponseUtils<TranferAdd> Tranfertopeople(TranferAdd tranferAdd, String fromCard) {
    //     try {
    //         // 参数校验
    //         if (tranferAdd == null || fromCard == null) {
    //             return new ResponseUtils<>(404, "参数不能为空");
    //         }
    //
    //         log.info("转账信息 - 付款人: {}, 收款人: {}, 金额: {}",
    //                 tranferAdd.getPayer(), tranferAdd.getPayee(), tranferAdd.getMoney());
    //
    //         // 验证转出账户
    //         if (moneyMapper.checkAccountExists(fromCard) == 0) {
    //             return new ResponseUtils<>(404, "转出账户不存在");
    //         }
    //
    //         // 验证转入账户
    //         if (moneyMapper.checkAccountExists(tranferAdd.getBankcard()) == 0) {
    //             return new ResponseUtils<>(404, "转入账户不存在");
    //         }
    //
    //         // 验证余额
    //         Double balance = moneyMapper.getBalance(fromCard);
    //         if (balance == null || balance < tranferAdd.getMoney()) {
    //             return new ResponseUtils<>(404, "余额不足");
    //         }
    //
    //         // 扣除转出方余额
    //         int deductResult = moneyMapper.deductBalance(fromCard, tranferAdd.getMoney());
    //         if (deductResult <= 0) {
    //             throw new RuntimeException("扣款失败");
    //         }
    //
    //         // 增加接收方余额
    //         int increaseResult = moneyMapper.increaseBalance(tranferAdd.getBankcard(), tranferAdd.getMoney());
    //         if (increaseResult <= 0) {
    //             throw new RuntimeException("收款失败");
    //         }
    //
    //         // 记录交易
    //         int recordResult = moneyMapper.Tranfertopeople(tranferAdd);
    //         if (recordResult <= 0) {
    //             throw new RuntimeException("记录交易失败");
    //         }
    //
    //         return new ResponseUtils<>(200, "转账成功");
    //
    //     } catch (Exception e) {
    //         log.error("转账处理失败", e);
    //         TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    //         return new ResponseUtils<>(500, "转账失败：" + e.getMessage());
    //     }
    // }
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

    @Override
    public int Instertrecharge(RechargeAdd rechargeAdd) {
        return 0;
    }

    // @Override
    // public int Instertrecharge(RechargeAdd rechargeAdd) {
    //     String bankcardbyname = usersMapper.bankcardbyname(rechargeAdd.getBank_card());
    //     RechargeAdd.setPayee(bankcardbyname);
    //     int result = moneyMapper.Instertrecharge(rechargeAdd);
    //     if(result>0){
    //         return 1;
    //     }else{
    //         return 0;
    //     }
    // }
}

