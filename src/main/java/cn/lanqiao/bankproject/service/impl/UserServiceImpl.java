package cn.lanqiao.bankproject.service.impl;

import cn.lanqiao.bankproject.entity.dto.LoginDTO;
import cn.lanqiao.bankproject.entity.po.User;
import cn.lanqiao.bankproject.mappers.UserMapper;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lanqiao.bankproject.service.UserService;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;


/*@author 比巴卜
@date  2025/1/1 下午4:06
@Description 用户表 登录、修改用户信息
*/
@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {


	@Autowired
	private UserMapper userMapper;

	// 使用Map存储验证码，key是用户名，value是验证码对象
	private final Map<String, VerifyCodeInfo> verifyCodeMap = new ConcurrentHashMap<>();

	@Data
	private static class VerifyCodeInfo {
		private final String code;        // 验证码
		private final long expireTime;    // 过期时间

		public VerifyCodeInfo(String code, long expireTime) {
			this.code = code;
			this.expireTime = expireTime;
		}
	}

	@Override
	public boolean checkUserExists(String username) {
		return userMapper.selectByUsername(username) != null;
	}
	@Override
	public User selectByUsername(String username) {
		return userMapper.selectByUsername(username);
	}

	@Override
	public void sendVerifyCode(String username) {
		// 验证账号是否存在
		User user = selectByUsername(username);
		if (user == null) {
			throw new RuntimeException("账号不存在");
		}

		// 生成6位随机验证码
		String verifyCode = String.format("%06d", new Random().nextInt(1000000));

		// 将验证码存入Map，5分钟后过期
		verifyCodeMap.put(username, new VerifyCodeInfo(
				verifyCode,
				System.currentTimeMillis() + 5 * 60 * 1000  // 5分钟后过期
		));

		// 在控制台打印验证码
		System.out.println("用户 [" + username + "] 的验证码是: " + verifyCode);
		System.out.println("验证码将在5分钟后过期");
	}
	@Override
	public User login(LoginDTO loginDTO) {
		// 验证账号密码
		User user = selectByUsername(loginDTO.getUsername());
		if (user == null) {
			throw new RuntimeException("账号不存在");
		}

		// 验证密码（实际应用中应该使用加密后的密码比较）
		if (!user.getPassword().equals(loginDTO.getPassword())) {
			throw new RuntimeException("密码错误");
		}

		// 验证验证码
		VerifyCodeInfo verifyCodeInfo = verifyCodeMap.get(loginDTO.getUsername());
		if (verifyCodeInfo == null) {
			throw new RuntimeException("请先获取验证码");
		}

		// 验证码是否过期
		if (System.currentTimeMillis() > verifyCodeInfo.getExpireTime()) {
			verifyCodeMap.remove(loginDTO.getUsername());
			throw new RuntimeException("验证码已过期，请重新获取");
		}

		// 验证码是否正确
		if (!verifyCodeInfo.getCode().equals(loginDTO.getVerifyCode())) {
			throw new RuntimeException("验证码错误");
		}
		// 验证通过后，删除验证码
		verifyCodeMap.remove(loginDTO.getUsername());

		return user;
	}
	@Override
	public User getUserByUsername(String username) {
		log.info("Getting user info for username: {}", username);
		User user = userMapper.selectByUsername(username);
		if (user == null) {
			log.error("User not found for username: {}", username);
			throw new RuntimeException("用户不存在");
		}
		log.info("Found user: {}", user);
		return user;
	}

	@Override
	public void updateUserInfo(User user) {
		log.info("开始更新用户信息: {}", user);

		// 获取当前登录的用户名（从前端传过来）
		String currentUsername = user.getCurrentUsername(); // 需要在User类中添加这个字段

		// 检查用户是否存在
		User existingUser = userMapper.selectByUsername(currentUsername);
		if (existingUser == null) {
			throw new RuntimeException("用户不存在");
		}

		// 如果要修改用户名，检查新用户名是否已被使用
		if (!currentUsername.equals(user.getUsername())) {
			User userByNewUsername = userMapper.selectByUsername(user.getUsername());
			if (userByNewUsername != null) {
				throw new RuntimeException("该用户名已被使用");
			}
		}

		// 更新用户信息
		int result = userMapper.updateUser(
				currentUsername,
				user.getUsername(),
				user.getPassword(),
				user.getPhone(),
				user.getEmail(),
				user.getAddress()
		); if (result != 1) {
			throw new RuntimeException("更新失败");
		}

		log.info("用户信息更新成功");
	}



	/**
	 *  将查询到的卡号做隐私处理 卡号有16位 开头4位和结尾4位显示，中间用*替代
	 */
	@Override
	public String privacyTreatment(String bankCard) {
		//限定卡号的长度为16
		if (bankCard != null && bankCard.length() != 16) {
			//归 0 处理
			String str = "****************";
			return str;
		}else if(bankCard == null){
			return null;
		}else {
			String newBankCard = bankCard.substring(0,4)+"********"+bankCard.substring(12,16);
			return newBankCard;
		}


	}


}