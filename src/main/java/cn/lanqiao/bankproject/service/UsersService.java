package cn.lanqiao.bankproject.service;

import cn.lanqiao.bankproject.entity.query.UsersQuery;
import cn.lanqiao.bankproject.entity.po.Users;
import cn.lanqiao.bankproject.utils.PageHelper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersService {
    PageHelper<Users> selectUsersList(String status, String username, int pageNum, int pageSize);
}
