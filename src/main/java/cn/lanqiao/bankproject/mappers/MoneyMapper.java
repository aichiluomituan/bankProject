package cn.lanqiao.bankproject.mappers;

import cn.lanqiao.bankproject.entity.dto.RechargeAdd;
import cn.lanqiao.bankproject.entity.dto.TranferAdd;
import cn.lanqiao.bankproject.entity.dto.Withdrawdel;
import cn.lanqiao.bankproject.entity.po.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface MoneyMapper {
    //转账
    // @Insert("Insert into transfer_record  values (null,#{toaccount},#{toname},#{amount},#{notes},'转账')")
    @Insert("Insert into trade  values (null,now(),#{payee},#{bankcard},#{money},'处理中',#{remark},'转账','张三')")
    int Tranfertopeople(TranferAdd tranferAdd);
    // 扣除转出方余额
    // @Update("UPDATE bank_info SET bank_balance = bank_balance - #{money} WHERE bank_card = #{fromCard} AND bank_balance >= #{money}")
    // int deductBalance(@Param("payer") String fromCard, @Param("money") double money);
    //增加接收方余额
    @Update("UPDATE bank_info SET bank_balance = bank_balance + #{money} WHERE bank_card = #{bankcard}")
    int increaseBalance(TranferAdd tranferAdd);
    //存款
    @Update("UPDATE bank_info SET bank_balance = bank_balance + #{bankBalance} WHERE bank_card = #{bankCard}")
    int UpdateBalance(RechargeAdd rechargeAdd);
    //取款
    @Update("UPDATE bank_info SET bank_balance = bank_balance - #{bankBalance} WHERE bank_card = #{bankCard}")
    int UpdateBalancedown(Withdrawdel withdrawdel);
    //判定密码
    @Select("SELECT trade_pwd FROM bank_info WHERE bank_card = #{bank_card}")
    String checkTradePwd(String bank_card);
    //插入存款记录
    int insertTradeRecord(RechargeAdd rechargeAdd);
    //查询姓名
    @Select("SELECT username FROM user WHERE bank_card = #{bankcard}")
    String getUsernameByBankCard(@Param("bankcard") String bankcard);
    //插入取款记录
    int insertTradeRecordwithdraw(Withdrawdel withdrawdel);
    //查询
    @Select("SELECT username FROM user WHERE bank_card = #{bankcard}")
    String getUsernameByBankCardtwo(@Param("bankcard") String bankcard);
}
