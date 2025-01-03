package cn.lanqiao.bankproject.service;

import cn.lanqiao.bankproject.entity.query.UsersQuery;
import cn.lanqiao.bankproject.entity.po.Users;
import cn.lanqiao.bankproject.utils.PageHelper;

public interface UsersService {
    PageHelper<Users> getUsersList(UsersQuery usersQuery);
}
