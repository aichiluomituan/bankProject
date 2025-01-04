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
    public PageHelper<Users> selectUsersList(String status, String username, int pageNum, int pageSize) {
        //一共有多少条数据
        // Integer totalnum = usersMapper.totalNum(status,username);
        //初始索引
        int offset=(pageNum-1)*pageSize;
        // List<TStudent> tStudents = tStudentMapper.pageList(classNo, name,pageNum, pageSize,offset);
        List<Users> users=usersMapper.selectUsersList(status,username,pageNum,pageSize,offset);
        //创建分页对象
        PageHelper<Users> pageHelper=new PageHelper<>();
        pageHelper.setPageSize(pageSize);
        pageHelper.setPageNum(pageNum);
        // pageHelper.setPages((int)Math.ceil((double)totalnum/pageSize));
        pageHelper.setList(users);
        return pageHelper;
    }
}
