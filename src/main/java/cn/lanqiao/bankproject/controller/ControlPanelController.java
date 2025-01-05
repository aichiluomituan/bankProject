package cn.lanqiao.bankproject.controller;

import cn.lanqiao.bankproject.common.Result;
import cn.lanqiao.bankproject.common.ResultCodeEnum;
import cn.lanqiao.bankproject.common.managementException;
import cn.lanqiao.bankproject.entity.dto.UserManagementDTO;
import cn.lanqiao.bankproject.entity.po.UserManagement;
import cn.lanqiao.bankproject.service.UserManagementService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/panel")

@Validated
@CrossOrigin
public class ControlPanelController {
@Autowired
private UserManagementService userManagementService;

    @PostMapping("/selectList")
    public Result selectList(@RequestBody @NotNull UserManagementDTO userManagementDTO) {
        List<UserManagement> UserManagementList =  userManagementService.selectList(userManagementDTO.getName());
        return Result.ok(UserManagementList);
    }
    @GetMapping("/{userId}")
    public Result selectOne(@PathVariable @NotNull Integer userId) {
        if (userId == null) {
            throw new managementException(ResultCodeEnum.PARAM_ERROR);
        }
        return Result.ok(userManagementService.selectOne(userId));
    }
    @PostMapping("/update")
    public Result update (@RequestBody @NotNull UserManagement userManagement) {
        userManagement.setUpdateTime(LocalDateTime.now());
        return Result.ok(userManagementService.saveOrUpdate(userManagement));
    }
    @GetMapping("/count")
    public Result count() {
        return Result.ok(userManagementService.selectUserCountAndMoneyCount());
    }
}
