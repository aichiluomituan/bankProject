package cn.lanqiao.bankproject.entity.vo;


import lombok.Builder;

@Builder
public record CaptchaVO(String captcha, String key) {}
