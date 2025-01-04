package cn.lanqiao.bankproject.mappers;


import cn.lanqiao.bankproject.entity.po.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface UsersMapper {
    //查所有记录条数
    // Integer totalNum(@Param("status") String status,@Param("username") String username);
    List<Users> selectUsersList(@Param("status") String status,@Param("username") String username,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("offset")int offset);
}
