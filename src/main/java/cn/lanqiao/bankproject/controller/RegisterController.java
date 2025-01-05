package cn.lanqiao.bankproject.controller;

import cn.lanqiao.bankproject.common.Result;
import cn.lanqiao.bankproject.common.ResultCodeEnum;
import cn.lanqiao.bankproject.common.managementException;
import cn.lanqiao.bankproject.constans.ConStantsFinal;
import cn.lanqiao.bankproject.entity.dto.RegisterDTO;
import cn.lanqiao.bankproject.entity.vo.CaptchaVO;
import cn.lanqiao.bankproject.service.RegisterUserService;
import cn.lanqiao.bankproject.utils.RedisUtils;
import com.wf.captcha.SpecCaptcha;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RequestMapping("/regis")
@RestController
@Validated
public class RegisterController {
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private RegisterUserService loginUserService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /*
    * 验证码获取
    * */
    @GetMapping(value = "/checkCode")
    public CaptchaVO checkCode() {
        SpecCaptcha captcha = new SpecCaptcha(100, 30,5);
        String code = captcha.text().toLowerCase();
        String checkCodeKey = ConStantsFinal.CODE_CACHE_KEY+UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(checkCodeKey,code,ConStantsFinal.REDIS_KEY_TIMEOUT, TimeUnit.SECONDS);
        return new CaptchaVO(captcha.toBase64(), checkCodeKey);
        }

    /*
    * 账号注册;
    * */
    @PostMapping("/Register")
    public Result register(@RequestBody @NotNull RegisterDTO registerDTO) {
        if (registerDTO == null) {
            throw new managementException(ResultCodeEnum.PARAMETER_ANOMALY);
        }
        loginUserService.Register(registerDTO);
        return Result.ok();   //成功;
    }
}
