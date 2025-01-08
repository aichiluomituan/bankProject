package cn.lanqiao.bankproject.service;

import cn.lanqiao.bankproject.entity.dto.RechargeAdd;
import cn.lanqiao.bankproject.entity.dto.TranferAdd;
import cn.lanqiao.bankproject.entity.dto.Withdrawdel;

public interface MoneyService {
    //转账功能
    int Tranfertopeople(TranferAdd tranferAdd);
    // ResponseUtils<TranferAdd> Tranfertopeople(TranferAdd tranferAdd, String fromCard);
    //增加余额
    int increaseBalance(TranferAdd tranferAdd);
    //存款功能
    int UpdateBalance(RechargeAdd rechargeAdd);
    // ResponseUtils<RechargeAdd> recharge(RechargeAdd rechargeAdd);
    //取款功能
    int UpdateBalancedown(Withdrawdel withdrawdel);
    //查询卡号
    String checkTradePwd(String bank_card);
    //核对密码
    boolean verifyTradePwd(String bank_card, String trade_pwd);
    //添加记录
    int Instertrecharge(RechargeAdd rechargeAdd);
    //插入取款记录
    int insertTradeRecordwithdraw(Withdrawdel withdrawdel);

}
