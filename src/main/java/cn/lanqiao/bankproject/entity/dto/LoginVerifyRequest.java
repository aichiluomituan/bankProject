package cn.lanqiao.bankproject.entity.dto;/*
@author 比巴卜
@date  2025/1/2 下午3:46
@Description 
*/

import lombok.Data;

@Data
public class LoginVerifyRequest {
    private String username;
    private String password;
}
