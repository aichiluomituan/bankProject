package cn.lanqiao.bankproject.service.impl;/*
@author 比巴卜
@date  2025/1/2 上午9:52
@Description 账户信息
*/


import cn.lanqiao.bankproject.entity.po.BankInfo;
import cn.lanqiao.bankproject.entity.po.User;
import cn.lanqiao.bankproject.mappers.AccountMapper;
import cn.lanqiao.bankproject.mappers.UserMapper;
import cn.lanqiao.bankproject.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public User getAccountInfo(String username) {
        log.info("查询账户信息，username: {}", username);
        User accountInfo = accountMapper.getAccountInfo(username);
        if (accountInfo == null) {
            log.error("账户不存在，username: {}", username);
            throw new RuntimeException("账户不存在");
        }
        return accountInfo;
    }

    @Override
    public boolean verifyLoginPassword(String username, String password) {
        if (org.springframework.util.StringUtils.isEmpty(username) || org.springframework.util.StringUtils.isEmpty(password)) {
            throw new IllegalArgumentException("用户名或密码不能为空");
        }

        User user = userMapper.selectByUsername(username);
        if (user == null) {
            log.error("用户不存在，username: {}", username);
            return false;
        }

        // 实际应用中应该使用加密后的密码比较
        boolean isValid = user.getPassword().equals(password);
        if (!isValid) {
            log.warn("密码错误，username: {}", username);
        }
        return isValid;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyTransactionPassword(String username, String newPassword) {
        if (org.springframework.util.StringUtils.isEmpty(username) || org.springframework.util.StringUtils.isEmpty(newPassword)) {
            throw new IllegalArgumentException("用户名或新密码不能为空");
        }

        // 直接更新交易密码
        int result = accountMapper.updateTradePwdByBankCard(username, newPassword);
        if (result != 1) {
            log.error("修改交易密码失败，username: {}", username);
            throw new RuntimeException("修改交易密码失败");
        }

        log.info("用户{}修改交易密码成功", username);
    }

    @Override
    public String getBankCardByUsername(String username) {
        if (org.springframework.util.StringUtils.isEmpty(username)) {
            throw new IllegalArgumentException("用户名不能为空");
        }

        String bankCard = accountMapper.getBankCardByUsername(username);
        if (bankCard == null) {
            log.error("未找到银行卡号，username: {}", username);
            throw new RuntimeException("未找到银行卡号");
        }

        log.info("获取银行卡号成功，username: {}, bankCard: {}", username, bankCard);
        return bankCard;
    }
}