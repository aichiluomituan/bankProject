package cn.lanqiao.bankproject.controller;/*
@author 比巴卜
@date  2025/1/7 上午11:14
@Description 
*/

import cn.lanqiao.bankproject.common.Result;
import cn.lanqiao.bankproject.entity.po.AdminUser;
import cn.lanqiao.bankproject.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adminUser")
@Validated
public class AdminUserController {
    @Autowired
    private AdminUserService AdminUserService;
    @RequestMapping("/login")
    public Result getLogin(@RequestBody AdminUser admin
                           ) {
        System.err.println(admin);
        AdminUserService.selectOne(admin);
return Result.ok();
    }
}
