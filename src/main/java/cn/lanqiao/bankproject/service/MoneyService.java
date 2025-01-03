package cn.lanqiao.bankproject.service;

import cn.lanqiao.bankproject.entity.dto.RechargeAdd;
import cn.lanqiao.bankproject.entity.dto.TranferAdd;
import cn.lanqiao.bankproject.entity.dto.Withdrawdel;

public interface MoneyService {
    //转账功能
    int Tranfertopeople(TranferAdd tranferAdd);
    //充值功能
    int UpdateBalance(RechargeAdd rechargeAdd);
    //提现功能
    int UpdateBalancedown(Withdrawdel withdrawdel);
    String checkTradePwd(String bank_card);
    boolean verifyTradePwd(String bank_card, String trade_pwd);
}
