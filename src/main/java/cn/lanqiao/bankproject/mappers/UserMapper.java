package cn.lanqiao.bankproject.mappers;

import cn.lanqiao.bankproject.entity.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
/*@author 比巴卜
@date  2025/1/1 下午4:06
@Description 用户表 登录、修改用户信息
*/
@Mapper
public interface UserMapper {


	@Select("SELECT * FROM user WHERE username = #{username}")
	User selectByUsername(@Param("username") String username);

	@Update({
			"<script>",
			"UPDATE user",
			"<set>",
			"    <if test='newUsername != null and newUsername != \"\"'>username = #{newUsername},</if>",
			"    <if test='password != null and password != \"\"'>password = #{password},</if>",
			"    <if test='phone != null and phone != \"\"'>phone = #{phone},</if>",
			"    <if test='email != null and email != \"\"'>email = #{email},</if>",
			"    <if test='address != null'>address = #{address}</if>",
			"</set>",
			"WHERE username = #{currentUsername}",
			"</script>"
	})
	int updateUser(@Param("currentUsername") String currentUsername,
				   @Param("newUsername") String newUsername,
				   @Param("password") String password,
				   @Param("phone") String phone,
				   @Param("email") String email,
				   @Param("address") String address);
}
