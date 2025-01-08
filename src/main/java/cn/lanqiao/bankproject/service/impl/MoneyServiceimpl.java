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
    //转账
    @Override
    public int Tranfertopeople(TranferAdd tranferAdd) {
        int result = moneyMapper.Tranfertopeople(tranferAdd);
        if (result > 0 ) {
            //转账成功
            return 1;
        } else {
            //转账失败
            return 0;
        }
    }
    //转账增肌余额
    @Override
    public int increaseBalance(TranferAdd tranferAdd) {
        int increaseResult = moneyMapper.increaseBalance(tranferAdd);
        if (increaseResult > 0 ) {
            //增加余额成功
            return 1;
        } else {
            //增加余额失败
            return 0;
        }
    }
    //存款更改余额
        @Override
        public int UpdateBalance(RechargeAdd rechargeAdd) {
            System.out.println("update调用mapper前"+rechargeAdd);
            int result = moneyMapper.UpdateBalance(rechargeAdd);
            System.out.println("update调用mapper后"+rechargeAdd);
            if(result>0){
                //充值成功
                return 1;
            }else {
                //充值失败
                return 0;
            }
        }
    //取款更改余额
    @Override
    public int UpdateBalancedown(Withdrawdel withdrawdel) {
        // 先验证密码
        if (!verifyTradePwd(withdrawdel.getBankCard(), withdrawdel.getTrade_pwd())) {
            return -1; // 密码错误返回-1
        }
        System.out.println("update调用mapper前"+withdrawdel);
        int result = moneyMapper.UpdateBalancedown(withdrawdel);
        System.out.println("update调用mapper后"+withdrawdel);
        if (result > 0) {
            //提现成功
            return 1;
        } else {
            //提现失败
            return 0;
        }
    }
    //获取密码
    @Override
    public String checkTradePwd(String bank_card) {
        return "";
    }
   //验证密码
    @Override
    public boolean verifyTradePwd(String bank_card, String trade_pwd) {
        String dbPassword = moneyMapper.checkTradePwd(bank_card);
        return dbPassword != null && dbPassword.equals(trade_pwd);
    }
    //存款加记录
    @Override
    public int Instertrecharge(RechargeAdd rechargeAdd) {
        System.out.println("调用mapper前"+rechargeAdd);
        rechargeAdd.setPayee(moneyMapper.getUsernameByBankCard(rechargeAdd.getBankCard()));
        System.out.println("调用mapper后"+rechargeAdd);
        int i = moneyMapper.insertTradeRecord(rechargeAdd);
        if (i > 0) {
            return 1;
        }else {
            return 0;
        }
    }
    //取款加记录
    @Override
    public int insertTradeRecordwithdraw(Withdrawdel withdrawdel) {
        System.out.println("调用mapper前"+withdrawdel);
        withdrawdel.setPayer(moneyMapper.getUsernameByBankCardtwo(withdrawdel.getBankCard()));
        System.out.println("调用mapper后"+withdrawdel);
        int i = moneyMapper.insertTradeRecordwithdraw(withdrawdel);
        if (i > 0) {
            return 1;
        }else {
            return 0;
        }
    }

}

