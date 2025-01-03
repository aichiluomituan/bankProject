package cn.lanqiao.bankproject.mappers;

import cn.lanqiao.bankproject.entity.dto.RechargeAdd;
import cn.lanqiao.bankproject.entity.dto.TranferAdd;
import cn.lanqiao.bankproject.entity.dto.Withdrawdel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface MoneyMapper {
    //转账
    @Insert("Insert into transfer_record  values (null,#{toaccount},#{toname},#{amount},#{notes})")
    int Tranfertopeople(TranferAdd tranferAdd);
    //充值
    @Update("UPDATE bank_info SET bank_balance = bank_balance + #{bank_balance} WHERE bank_card = #{bank_card}")
    int UpdateBalance(RechargeAdd rechargeAdd);
    //提现
    @Update("UPDATE bank_info SET bank_balance = bank_balance - #{bank_balance} WHERE bank_card = #{bank_card}")
    int UpdateBalancedown(Withdrawdel withdrawdel);
    //判定密码
    @Select("SELECT trade_pwd FROM bank_info WHERE bank_card = #{bank_card}")
    String checkTradePwd(String bank_card);
}
