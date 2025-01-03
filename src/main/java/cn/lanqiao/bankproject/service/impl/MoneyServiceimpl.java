package cn.lanqiao.bankproject.service.impl;

import cn.lanqiao.bankproject.mappers.MoneyMapper;
import cn.lanqiao.bankproject.service.MoneyService;
import cn.lanqiao.bankproject.entity.dto.RechargeAdd;
import cn.lanqiao.bankproject.entity.dto.TranferAdd;
import cn.lanqiao.bankproject.entity.dto.Withdrawdel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MoneyServiceimpl implements MoneyService {
    @Autowired
    private MoneyMapper moneyMapper;
    @Override
    public int Tranfertopeople(TranferAdd tranferAdd) {
        int result = moneyMapper.Tranfertopeople(tranferAdd);
            if(result>0){
                //转账成功
                return 1;
            }else {
                //转账失败
                return 0;
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
