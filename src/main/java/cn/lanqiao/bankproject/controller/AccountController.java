package cn.lanqiao.bankproject.controller;/*
@author 比巴卜
@date  2025/1/2 上午9:51
@Description 账户信息
*/

import cn.lanqiao.bankproject.entity.po.User;
import cn.lanqiao.bankproject.service.AccountService;
import cn.lanqiao.bankproject.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/account")
@CrossOrigin(origins = "*")  // 添加跨域注解
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/getAccountInfo")
    public ResponseUtils<User> getAccountInfo(@RequestParam String username) {
        try {
            log.info("开始获取账户信息，username: {}", username);

            User accountInfo = accountService.getAccountInfo(username);

            if (accountInfo == null) {
                return ResponseUtils.error(404, "账户不存在");
            }

            return ResponseUtils.success("获取成功", accountInfo);
        } catch (Exception e) {
            log.error("获取账户信息失败", e);
            return ResponseUtils.error(500, "系统错误：" + e.getMessage());
        }
    }

    @PostMapping("/verifyLoginPassword")
    public ResponseUtils verifyLoginPassword(@RequestBody Map<String, String> params) {
        try {
            String username = params.get("username");
            String password = params.get("password");

            if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
                return ResponseUtils.error(400, "用户名或密码不能为空");
            }

            boolean isValid = accountService.verifyLoginPassword(username, password);
            return isValid ? ResponseUtils.success( "验证成功") : ResponseUtils.error(401, "登录密码错误");
        } catch (Exception e) {
            log.error("验证登录密码失败", e);
            return ResponseUtils.error(500, "验证失败：" + e.getMessage());
        }
    }

    @PostMapping("/modifyTransactionPassword")
    public ResponseUtils modifyTransactionPassword(@RequestBody Map<String, String> params) {
        try {
            String username = params.get("username");
            String newPassword = params.get("newPassword");

            if (StringUtils.isEmpty(username) || StringUtils.isEmpty(newPassword)) {
                return ResponseUtils.error(400, "用户名或新密码不能为空");
            }

            accountService.modifyTransactionPassword(username, newPassword);
            return ResponseUtils.success( "交易密码修改成功");
        } catch (Exception e) {
            log.error("修改交易密码失败", e);
            return ResponseUtils.error(500, "修改失败：" + e.getMessage());
        }
    }
    @GetMapping("/getBankCardByUsername")
    public ResponseEntity<?> getBankCardByUsername(@RequestParam String username) {
        try {
            String bankCard = accountService.getBankCardByUsername(username);
            return ResponseEntity.ok().body(Map.of("code", 200, "data", bankCard));
        } catch (Exception e) {
            log.error("获取银行卡号失败，username: {}", username, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("code", 500, "msg", e.getMessage()));
        }
    }
    @PostMapping("/updateBankCard")
    public ResponseEntity<String> updateBankCard(@RequestParam String currentUsername, @RequestParam String bankCard) {
        try {
            // 检查银行卡号是否为空或无效
            if (bankCard == null || bankCard.trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("银行卡号不能为空");
            }

            // 如果银行卡号有效，进行更新操作
            accountService.updateBankCard(currentUsername, bankCard);
            // 3. 如果更新成功，返回200 OK和成功消息
            return ResponseEntity.status(HttpStatus.OK).body("银行卡号更新成功");

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
