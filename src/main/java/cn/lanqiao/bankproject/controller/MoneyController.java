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
        // return moneyService.Tranfertopeople(tranferAdd, fromCard);
        // 参数验证
        //     if (fromCard == null || fromCard.trim().isEmpty()) {
        //         return new ResponseUtils<>(404, "转出账号不能为空");
        //     }
        //     try {
        //         return moneyService.Tranfertopeople(tranferAdd, fromCard);
        //     } catch (Exception e) {
        //         log.error("转账处理异常", e);
        //         return new ResponseUtils<>(500, "转账失败：" + e.getMessage());
        //     }
        // }
        int tranfertopeople = moneyService.Tranfertopeople(tranferAdd);
        if (tranfertopeople == 1) {
            //转账成功
            return new ResponseUtils<TranferAdd>(200, "转账成功");
        } else {
            //转账失败
            return new ResponseUtils<TranferAdd>(404, "转账失败");
        }
    }
    //充值功能
    @RequestMapping("/recharge")
    public  ResponseUtils<RechargeAdd> recharge(@RequestBody RechargeAdd rechargeAdd) {
        int rechargemoney = moneyService.UpdateBalance(rechargeAdd);
        if(rechargemoney==1){
            //转账成功
            return new ResponseUtils<RechargeAdd>(200,"充值成功");
        }else{
            //转账失败
            return new ResponseUtils<RechargeAdd>(404,"充值失败");
        }
    }

    //提现功能
    @RequestMapping("/withdraw")
    public ResponseUtils<Withdrawdel> withdraw(@RequestBody Withdrawdel withdrawdel) {
        int withdrawmoney = moneyService.UpdateBalancedown(withdrawdel);
        if(withdrawmoney == 1){
            return new ResponseUtils<Withdrawdel>(200,"提现成功");
        } else if(withdrawmoney == -1) {
            return new ResponseUtils<Withdrawdel>(404,"支付密码错误");
        } else {
            return new ResponseUtils<Withdrawdel>(404,"提现失败");
        }
    }

}
