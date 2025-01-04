package cn.lanqiao.bankproject.service;/*
@author 比巴卜
@date  2025/1/3 下午7:45
@Description 用户概览
*/

import cn.lanqiao.bankproject.entity.po.BankInfo;
import cn.lanqiao.bankproject.entity.po.Trade;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AccountOverviewService {
    BankInfo getAccountInfo(String username);
    Double getTotalExpenditure(String username);
    Double getTotalIncome(String username);
    List<Trade> getRecentTrades(String username);
}
