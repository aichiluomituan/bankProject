package cn.lanqiao.bankproject.controller;/*
@author 比巴卜
@date  2025/1/3 下午7:44
@Description 用户概览
*/

import cn.lanqiao.bankproject.entity.po.BankInfo;
import cn.lanqiao.bankproject.entity.po.Trade;
import cn.lanqiao.bankproject.service.AccountOverviewService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountView")
@CrossOrigin(origins = "*")  // 添加跨域注解
public class AccountOverviewController {
    @Resource
    private AccountOverviewService accountOverviewService;

    private static final Logger logger = LoggerFactory.getLogger(AccountOverviewController.class);
    @GetMapping("/info")
    public BankInfo getAccountInfo(@RequestParam String username) {
        return accountOverviewService.getAccountInfo(username);
    }
    @GetMapping("/totalExpenditure")
    public ResponseEntity<Double> getTotalExpenditure(@RequestParam String username) {
        Double totalExpenditure = accountOverviewService.getTotalExpenditure(username);
        System.out.println("Total Expenditure: " + totalExpenditure);
        return ResponseEntity.ok(totalExpenditure != null ? totalExpenditure : 0.0);
    }

    @GetMapping("/totalIncome")
    public ResponseEntity<Double> getTotalIncome(@RequestParam String username) {
        Double totalIncome = accountOverviewService.getTotalIncome(username);
        System.out.println("Total Income: " + totalIncome);
        return ResponseEntity.ok(totalIncome != null ? totalIncome : 0.0);
    }
    @GetMapping("/tradeRecord")
    public ResponseEntity<List<Trade>> getRecentTrades(@RequestParam String username) {
        List<Trade> trades = accountOverviewService.getRecentTrades(username);
        // 将集合遍历打印在控制台
        trades.forEach(trade -> logger.info(trade.toString())); // 使用日志记录
        return ResponseEntity.ok(trades);
    }
}
