package cn.lanqiao.bankproject.mappers;


import lombok.Builder;

@Builder
public record CaptchaVO(String captcha, String key) {}
