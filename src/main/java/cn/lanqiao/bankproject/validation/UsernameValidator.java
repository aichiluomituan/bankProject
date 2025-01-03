package cn.lanqiao.bankproject.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
/*@author 比巴卜
@date  2025/1/1 下午4:06
@Description 验证用户名
*/
public class UsernameValidator implements ConstraintValidator<ValidUsername, String> {

    @Override
    public void initialize(ValidUsername constraintAnnotation) {
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (username == null) {
            return false;
        }
        // 验证用户名格式：字母开头，允许字母数字下划线，长度6-20
        return username.matches("^[a-zA-Z][a-zA-Z0-9_]{5,19}$");
    }
}