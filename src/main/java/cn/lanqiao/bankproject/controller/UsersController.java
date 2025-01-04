package cn.lanqiao.bankproject.controller;

import cn.lanqiao.bankproject.entity.query.PageHelperQuery;
import cn.lanqiao.bankproject.service.UsersService;
import cn.lanqiao.bankproject.utils.ResponseUtils;
import cn.lanqiao.bankproject.mappers.UsersMapper;
import cn.lanqiao.bankproject.service.UsersService;
import cn.lanqiao.bankproject.entity.query.UsersQuery;
import cn.lanqiao.bankproject.entity.po.Users;
import cn.lanqiao.bankproject.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Users")
public class UsersController {
    @Autowired
    private UsersService usersService;
    @RequestMapping("/list")
    public ResponseUtils list(@RequestBody PageHelperQuery pageHelperQuery) {
        PageHelper<Users> pageHelper = usersService.selectUsersList(pageHelperQuery.getStatus(),pageHelperQuery.getUsername(),pageHelperQuery.getPageNum(),pageHelperQuery.getPageSize());
        return new ResponseUtils<>(200, "分页查询成功成功", pageHelper);
    }
}