package cn.lanqiao.bankproject.controller;

import cn.lanqiao.bankproject.entity.dto.LoginDTO;
import cn.lanqiao.bankproject.entity.po.User;
import cn.lanqiao.bankproject.entity.query.UserQuery;
import cn.lanqiao.bankproject.entity.vo.ResponseVO;
import cn.lanqiao.bankproject.service.UserService;
import cn.lanqiao.bankproject.utils.ResponseUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
/*@author 比巴卜
@date  2025/1/1 下午4:06
@Description 用户表 登录、修改用户信息
*/
@Slf4j
@RestController("userController")
@RequestMapping("/user")
@CrossOrigin(origins = "*")  // 添加跨域注解
public class UserController{

	@Resource
	private UserService userService;
	@GetMapping("/getUserInfo")
	public ResponseUtils<User> getUserInfo(@RequestParam String username) {
		try {
			log.info("收到获取用户信息请求，username: {}", username);

			if (username == null || username.trim().isEmpty()) {
				log.warn("用户名为空");
				return ResponseUtils.error(400, "用户名不能为空");
			}

			User user = userService.getUserByUsername(username);
			log.info("查询到的用户信息: {}", user);

			if (user == null) {
				log.warn("未找到用户信息");
				return ResponseUtils.error(404, "用户不存在");
			}

			return ResponseUtils.success("获取成功", user);
		} catch (Exception e) {
			log.error("获取用户信息失败", e);
			return ResponseUtils.error(500, e.getMessage());
		}
	}

	@PostMapping("/updateUserInfo")
	public ResponseUtils<Void> updateUserInfo(@RequestBody User user) {
		try {
			log.info("Updating user info: {}", user);
			userService.updateUserInfo(user);
			return ResponseUtils.success("更新成功");
		} catch (Exception e) {
			log.error("Error updating user info: ", e);
			return ResponseUtils.error(500, e.getMessage());
		}
	}
	@PostMapping("/sendVerifyCode")
	public ResponseUtils<Void> sendVerifyCode(@RequestBody Map<String, String> params) {
		try {
			String username = params.get("username");
			if (username == null || username.trim().isEmpty()) {
				return ResponseUtils.error(400, "用户名不能为空");
			}

			// 先检查用户是否存在
			if (!userService.checkUserExists(username)) {
				return ResponseUtils.error(400, "账号不存在");
			}

			userService.sendVerifyCode(username);
			return ResponseUtils.success("验证码发送成功");
		} catch (Exception e) {
			log.error("发送验证码失败", e);
			return ResponseUtils.error(500, e.getMessage());
		}
	}
	@PostMapping("/login")
	public ResponseUtils<User> login(@RequestBody LoginDTO loginDTO) {
		try {
			if (loginDTO == null ||
					loginDTO.getUsername() == null ||
					loginDTO.getPassword() == null ||
					loginDTO.getVerifyCode() == null) {
				return ResponseUtils.error(400, "请填写完整的登录信息");
			}

			User user = userService.login(loginDTO);
			return ResponseUtils.success("登录成功", user);

		} catch (Exception e) {
			log.error("登录失败", e);
			return ResponseUtils.error(500, e.getMessage());
		}
	}
}