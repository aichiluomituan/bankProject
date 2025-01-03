package cn.lanqiao.bankproject.service.impl;

import cn.lanqiao.bankproject.mappers.UsersMapper;
import cn.lanqiao.bankproject.service.UsersService;
import cn.lanqiao.bankproject.entity.query.UsersQuery;
import cn.lanqiao.bankproject.entity.po.Users;
import cn.lanqiao.bankproject.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceimpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public PageHelper<Users> getUsersList(UsersQuery query) {
        // // 计算偏移量
        // query.setOffset((query.getPageNum() - 1) * query.getPageSize());
        //
        // // 查询总记录数
        // int total = usersMapper.countUsers(query);
        //
        // // 查询数据列表
        // List<Users> list = usersMapper.selectUsersList(query);
        //
        // // 封装分页结果
        // PageHelper<Users> pageHelper = new PageHelper<>();
        // pageHelper.setList(list);
        // pageHelper.setTotal(total);
        // pageHelper.setPageNum(query.getPageNum());
        // pageHelper.setPageSize(query.getPageSize());
        // pageHelper.setPages((total + query.getPageSize() - 1) / query.getPageSize());

        // return pageHelper;
        return null;
    }
}
