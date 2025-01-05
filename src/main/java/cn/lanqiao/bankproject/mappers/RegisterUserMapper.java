package cn.lanqiao.bankproject.mappers;

import cn.lanqiao.bankproject.entity.po.RegisterUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RegisterUserMapper extends BaseMapper<RegisterUser> {
    @Select("select * from user where username = #{username}")
    RegisterUser findByUsername(String username);
}
