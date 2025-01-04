package cn.lanqiao.bankproject.mappers;

import cn.lanqiao.bankproject.entity.dto.RechargeAdd;
import cn.lanqiao.bankproject.entity.dto.TranferAdd;
import cn.lanqiao.bankproject.entity.dto.Withdrawdel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface MoneyMapper {
    //转账
    // @Insert("Insert into transfer_record  values (null,#{toaccount},#{toname},#{amount},#{notes},'转账')")
    @Insert("Insert into trade  values (null,now(),#{payee},#{bankcard},#{money},'成功',#{remark},'转账','张三')")
    int Tranfertopeople(TranferAdd tranferAdd);
    // 扣除转出方余额
    @Update("UPDATE bank_info SET bank_balance = bank_balance - #{money} WHERE bank_card = #{fromCard}")
    int deductBalance(@Param("fromCard") String fromCard, @Param("money") double money);

    // 增加接收方余额
    @Update("UPDATE bank_info SET bank_balance = bank_balance + #{money} WHERE bank_card = #{bankcard}")
    int increaseBalance(@Param("bankcard") String bankcard, @Param("money") double money);

    // 查询账户余额
    @Select("SELECT bank_balance FROM bank_info WHERE bank_card = #{bankCard}")
    Double getBalance(String bankCard);

    // 验证账户是否存在
    @Select("SELECT COUNT(*) FROM bank_info WHERE bank_card = #{bankCard}")
    int checkAccountExists(String bankCard);

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
