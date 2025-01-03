package cn.lanqiao.bankproject.entity.dto;/*
@author 比巴卜
@date  2025/1/2 上午9:54
@Description 修改密码
*/

import lombok.Data;

@Data
public class PasswordUpdateDTO {
    private String username;
    private String oldPassword;
    private String newPassword;
}