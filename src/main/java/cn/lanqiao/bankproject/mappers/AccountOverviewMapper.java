package cn.lanqiao.bankproject.mappers;/*
@author 比巴卜
@date  2025/1/3 下午7:46
@Description 用户概览
*/

import cn.lanqiao.bankproject.entity.po.Trade;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AccountOverviewMapper {
    // 查询用户银行卡信息
    @Select("SELECT bank_card FROM user WHERE username = #{username}")
    String selectBankCard(String username);

    // 查询用户余额
    @Select("SELECT b.bank_balance FROM bank_info b JOIN user u ON b.bank_card = u.bank_card WHERE u.username = #{username}")
    Double selectBankBalance(String username);
    // 查询这个月用户总支出多少元
    @Select("SELECT SUM(money) AS total_expenditure FROM trade WHERE payer = #{username} AND trade_type IN ('取款', '转账') AND MONTH(date) = MONTH(CURRENT_DATE()) AND YEAR(date) = YEAR(CURRENT_DATE()) AND trade_state = '成功'")
    Double selectTotalExpenditure(String username);
    // 查询这个月用户总收入多少元
    @Select("SELECT SUM(money) AS total_income FROM trade WHERE payee = #{username} AND trade_type IN ('存款','转账') AND MONTH(date) = MONTH(CURRENT_DATE()) AND YEAR(date) = YEAR(CURRENT_DATE()) AND trade_state = '成功'")
    Double selectTotalIncome(String username);
    // 查询当前用户的所有交易记录，包括转账、存款、取款 date,trade_type,money,remark,payer,payee
    @Select("SELECT * FROM trade WHERE (payer = #{username} OR payee = #{username}) " +
            "AND trade_state = '成功' " +
            "AND MONTH(date) = MONTH(CURRENT_DATE()) " +
            "AND YEAR(date) = YEAR(CURRENT_DATE()) " +
            "ORDER BY date DESC LIMIT 5")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "date", property = "date"),
            @Result(column = "bankcard", property = "bankCard"),
            @Result(column = "trade_type", property = "tradeType"),
            @Result(column = "money", property = "money"),
            @Result(column = "trade_state", property = "tradeState"),
            @Result(column = "remark", property = "remark"),
            @Result(column = "payer", property = "payer"),
            @Result(column = "payee", property = "payee")
    })
    List<Trade> selectTradeRecord(@Param("username")String username);
}
