package cn.lanqiao.bankproject.service.impl;

import cn.lanqiao.bankproject.entity.Edit.UsersEdit;
import cn.lanqiao.bankproject.mappers.UsersMapper;
import cn.lanqiao.bankproject.service.UsersService;
import cn.lanqiao.bankproject.entity.query.UsersQuery;
import cn.lanqiao.bankproject.entity.po.Users;
import cn.lanqiao.bankproject.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersServiceimpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public PageHelper<Users> selectUsersList(String status, String username, int pageNum, int pageSize) {
        int totalNum = usersMapper.totalNum();
        int offset = (pageNum - 1) * pageSize;
        List<Users> users = usersMapper.selectUsersList(status, username, pageNum, pageSize, offset);
        PageHelper<Users> pageHelper = new PageHelper<>();
        pageHelper.setPageSize(pageSize);
        pageHelper.setPageNum(pageNum);
        pageHelper.setPages((int) Math.ceil((double) totalNum / pageSize));
        pageHelper.setList(users);
        return pageHelper;
    }


    @Override
    public int update(UsersEdit usersEdit) {
        int update = usersMapper.update(usersEdit);
        if(update>0){
            return 1;
        }
        return 0;
    }
    }
