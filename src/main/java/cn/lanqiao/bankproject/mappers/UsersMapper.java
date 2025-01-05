package cn.lanqiao.bankproject.mappers;


import cn.lanqiao.bankproject.entity.po.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
@Mapper
public interface UsersMapper {
    //查所有记录条数
    //,@Param("username") String username
    //@Param("status") String status
    @Select("select COUNT(*) from user")
    int totalNum();
    List<Users> selectUsersList(@Param("status") String status,@Param("username") String username,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("offset")int offset);
    //修改锁定
    @Update( "update users set status = #{status} WHERE id = #{id}")
    int updatestatus(@Param("id") Long id,@Param("status") String status);
}
