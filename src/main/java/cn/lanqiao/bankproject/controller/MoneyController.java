package cn.lanqiao.bankproject.controller;

import cn.lanqiao.bankproject.entity.dto.RechargeAdd;
import cn.lanqiao.bankproject.entity.dto.TranferAdd;
import cn.lanqiao.bankproject.entity.dto.Withdrawdel;
import cn.lanqiao.bankproject.service.MoneyService;
import cn.lanqiao.bankproject.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/money")
@Slf4j
public class MoneyController {
    @Autowired
    private MoneyService moneyService;
    //转账功能
    @RequestMapping("/transfer")
    public ResponseUtils<TranferAdd> transfer(@RequestBody TranferAdd tranferAdd) {
        int tranfertopeople = moneyService.Tranfertopeople(tranferAdd);
        int increaseBalance = moneyService.increaseBalance(tranferAdd);
        if (tranfertopeople == 1 && increaseBalance == 1) {
            //转账成功
            return new ResponseUtils<TranferAdd>(200, "转账成功");
        } else {
            //转账失败
            return new ResponseUtils<TranferAdd>(404, "转账失败");
        }
    }
    //存款功能
    @RequestMapping("/recharge")
    public  ResponseUtils<RechargeAdd> recharge(@RequestBody RechargeAdd rechargeAdd) {
        System.out.println("controller:"+rechargeAdd);
        int rechargemoney = moneyService.UpdateBalance(rechargeAdd);
        int instertrecharge = moneyService.Instertrecharge(rechargeAdd);
        if(rechargemoney==1 && instertrecharge==1){
            //转账成功
            return new ResponseUtils<>(200,"充值成功");
        }else{
            //转账失败
            return new ResponseUtils<>(404,"充值失败");
        }
    }


    //取款功能
    @RequestMapping("/withdraw")
    public ResponseUtils<Withdrawdel> withdraw(@RequestBody Withdrawdel withdrawdel) {
        int withdrawmoney = moneyService.UpdateBalancedown(withdrawdel);
        int insertTradeRecordwithdraw = moneyService.insertTradeRecordwithdraw(withdrawdel);
        if(withdrawmoney == 1 && insertTradeRecordwithdraw==1){
            return new ResponseUtils<Withdrawdel>(200,"提现成功");
        } else if(withdrawmoney == -1) {
            return new ResponseUtils<Withdrawdel>(404,"支付密码错误");
        } else {
            return new ResponseUtils<Withdrawdel>(404,"提现失败");
        }
    }

}
