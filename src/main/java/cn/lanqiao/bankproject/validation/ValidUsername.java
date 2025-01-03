package cn.lanqiao.bankproject.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameValidator.class)
@Documented
public @interface ValidUsername {
    String message() default "无效的用户名格式";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}