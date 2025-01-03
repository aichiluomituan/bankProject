package cn.lanqiao.bankproject.mappers;


import cn.lanqiao.bankproject.entity.query.UsersQuery;
import cn.lanqiao.bankproject.entity.po.Users;

import java.util.List;

public interface UsersMapper {
    List<Users> selectUsersList(UsersQuery usersQuery);
    int countUsers(UsersQuery usersQuery);
}
