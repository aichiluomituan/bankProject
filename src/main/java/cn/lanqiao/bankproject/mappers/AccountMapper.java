package cn.lanqiao.bankproject.mappers;/*
@author 比巴卜
@date  2025/1/2 上午9:53
@Description 账户信息
*/


import cn.lanqiao.bankproject.entity.po.BankInfo;
import cn.lanqiao.bankproject.entity.po.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.repository.query.Param;


@Mapper
public interface AccountMapper {

    @Select("SELECT username, status FROM user WHERE username = #{username}")
    User getAccountInfo(String username);

    /**
     * 更新交易密码
     */
    @Update("UPDATE bank_info SET trade_pwd = #{newPassword} WHERE bank_card = (SELECT bank_card FROM user WHERE username = #{username})")
    int updateTradePwdByBankCard(@Param("username") String username,
                                 @Param("newPassword") String newPassword);
    // 通过User表查询银行卡账号
    @Select("SELECT bank_card FROM user WHERE username = #{username}")
    String getBankCardByUsername(String username);
    // 更新银行卡账号
    @Update("UPDATE user SET bank_card = #{bankCard} WHERE username = #{currentUsername}")
    int updateUserBankCard(@Param("currentUsername") String currentUsername, @Param("bankCard") String bankCard);
}