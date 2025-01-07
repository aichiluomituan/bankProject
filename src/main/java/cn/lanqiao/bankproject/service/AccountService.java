package cn.lanqiao.bankproject.service;/*
@author 比巴卜
@date  2025/1/2 上午10:02
@Description 
*/
import cn.lanqiao.bankproject.entity.po.User;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.repository.query.Param;

public interface AccountService {

    /**
     * 获取账户信息
     * @param username 用户名
     * @return 账户信息
     */
    User getAccountInfo(String username);
    /**
     * 验证登录密码
     */
    boolean verifyLoginPassword(String username, String password);

    /**
     * 修改交易密码
     */
    void modifyTransactionPassword(String username, String newPassword);
    /**
     * 根据用户名获取银行卡号
     */
    String getBankCardByUsername(String username);
    // 修改银行卡账号
    void updateBankCard(String currentUsername, String bankCard);

}