package cn.lanqiao.bankproject.service.impl;

import cn.lanqiao.bankproject.common.ResultCodeEnum;
import cn.lanqiao.bankproject.common.managementException;
import cn.lanqiao.bankproject.entity.dto.RegisterDTO;
import cn.lanqiao.bankproject.entity.po.RegisterUser;
import cn.lanqiao.bankproject.mappers.RegisterUserMapper;
import cn.lanqiao.bankproject.service.RegisterUserService;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegisterUserServiceImpl extends ServiceImpl<RegisterUserMapper, RegisterUser> implements RegisterUserService {
    private static final Logger logger = LoggerFactory.getLogger(RegisterUserServiceImpl.class);
@Autowired
private RegisterUserMapper registerUserMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String PRIVATE_PASSWORD_EXCLUDED = "password";
    @Override
    public void Register(RegisterDTO registerDTO) {
        logger.info("账号开始校验");
        if (Objects.isNull(registerDTO.username())) {
            throw new managementException(ResultCodeEnum.INCORRECT_ID_CODE_USERNAME);
        }
//        LambdaQueryWrapper<RegisterUser> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(RegisterUser::username, registerDTO.username());
//        RegisterUser userMessenger = registerUserMapper.selectOne(wrapper);
        RegisterUser userMessenger = registerUserMapper.findByUsername(registerDTO.username());
        if (!Objects.isNull(userMessenger)) {
            throw new managementException(ResultCodeEnum.ADMIN_ACCOUNT_EXIST_ERROR);
        }
        if (!isValidPhoneNumber(registerDTO.phone())) {
            throw new managementException(ResultCodeEnum.APP_LOGIN_PHONE_EMPTY_NOT);
        }
        String codeKey = stringRedisTemplate.opsForValue().get(registerDTO.key());
        logger.info("redis中获取验证码:{}", codeKey);
        if (StringUtils.isEmpty(codeKey)) {
            throw new managementException(ResultCodeEnum.APP_LOGIN_CODE_EXPIRED);
        }
        if (!CaptchaTrueAndFalse(codeKey, registerDTO.code())) {
            throw new managementException(ResultCodeEnum.APP_LOGIN_CODE_ERROR);
        }
        logger.info("1:{},2:{}", registerDTO.password(), registerDTO.psd());
        if (!PasswordTrueAndFalse(registerDTO.password(), registerDTO.psd())) {
            throw new managementException(ResultCodeEnum.THE_PASSWORD_IS_DIFFERENT_FOR_THE_TWO_TIMES);
        }
        RegisterUser build = RegisterUser
                    .builder()
                .password(DigestUtils.md5DigestAsHex(registerDTO.password().getBytes()))
                .build();
        BeanUtils.copyProperties(registerDTO, build, PRIVATE_PASSWORD_EXCLUDED);
        logger.info("添加到数据库");
        super.save(build);
    }

    private Boolean CaptchaTrueAndFalse(String codeKey, String code) {
        return codeKey.equalsIgnoreCase(code);
    }

    private Boolean PasswordTrueAndFalse(String psd, String password) {
        return password.equals(psd);
    }
    private static boolean isValidPhoneNumber(String phoneNumber) {
        String regex =  "^(?:\\+86)?1[3-9]\\d{9}$";

        //18224229203
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
