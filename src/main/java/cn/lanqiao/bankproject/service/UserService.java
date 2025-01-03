package cn.lanqiao.bankproject.service;

import cn.lanqiao.bankproject.entity.dto.LoginDTO;
import cn.lanqiao.bankproject.entity.po.User;
import cn.lanqiao.bankproject.entity.query.UserQuery;
import cn.lanqiao.bankproject.entity.vo.PaginationResultVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/*@author 比巴卜
@date  2025/1/1 下午4:06
@Description 用户表 登录、修改用户信息
*/
public interface UserService {


	/**
	 * 检查用户是否存在
	 */
	boolean checkUserExists(String username);

	/**
	 * 发送验证码
	 */
	void sendVerifyCode(String username);

	/**
	 * 用户登录
	 */
	User login(LoginDTO loginDTO);

	/**
	 * 根据用户名查询用户
	 */
	User selectByUsername(String username);
	User getUserByUsername(String username);
	void updateUserInfo(User user);


}