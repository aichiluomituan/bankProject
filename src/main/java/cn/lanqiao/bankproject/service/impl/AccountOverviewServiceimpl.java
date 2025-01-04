package cn.lanqiao.bankproject.service.impl;/*
@author 比巴卜
@date  2025/1/3 下午7:45
@Description 用户概览
*/


import cn.lanqiao.bankproject.entity.po.BankInfo;
import cn.lanqiao.bankproject.entity.po.Trade;
import cn.lanqiao.bankproject.mappers.AccountOverviewMapper;
import cn.lanqiao.bankproject.service.AccountOverviewService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountOverviewServiceimpl implements AccountOverviewService {
    @Resource
    private AccountOverviewMapper accountOverviewMapper;
    public BankInfo getAccountInfo(String username) {
        try {
            // 查询银行卡号
            String bankCard = accountOverviewMapper.selectBankCard(username);
            System.out.println(bankCard);
            if (bankCard == null) {
                throw new RuntimeException("未找到用户的银行卡信息");
            }
            // 查询余额
            Double bankBalance = accountOverviewMapper.selectBankBalance(username);
            System.out.println(bankBalance);
            if (bankBalance == null) {
                throw new RuntimeException("未找到用户的银行余额信息");
            }
            return new BankInfo(bankCard, bankBalance);
        } catch (Exception e) {
            // 记录异常日志
            System.err.println("获取账户信息时发生错误: " + e.getMessage());
            // 可以选择抛出自定义异常
            throw new RuntimeException("获取账户信息失败，请稍后重试");
        }
    }

    @Override
    public Double getTotalExpenditure(String username) {
        return accountOverviewMapper.selectTotalExpenditure(username);
    }

    @Override
    public Double getTotalIncome(String username) {
        return accountOverviewMapper.selectTotalIncome(username);
    }
    @Override
    public List<Trade> getRecentTrades(String username) {
        return accountOverviewMapper.selectTradeRecord(username);
    }
}
