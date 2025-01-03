package cn.lanqiao.bankproject.entity.enums;/*
@author 比巴卜
@date  2025/1/2 上午10:12
@Description 用户状态枚举类
*/

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum UserStatus {

    NORMAL(1, "正常", "账户正常使用"),
    LOCKED(2, "锁定", "账户已被锁定"),
    FROZEN(3, "冻结", "账户已被冻结"),
    DISABLED(4, "禁用", "账户已被禁用"),
    CANCELLED(5, "注销", "账户已注销");

    private final Integer code;
    private final String status;
    private final String description;

    UserStatus(Integer code, String status, String description) {
        this.code = code;
        this.status = status;
        this.description = description;
    }

    /**
     * 根据状态码获取枚举值
     */
    public static UserStatus getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (UserStatus status : UserStatus.values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }

    /**
     * 根据状态名称获取枚举值
     */
    public static UserStatus getByStatus(String status) {
        if (status == null) {
            return null;
        }
        for (UserStatus userStatus : UserStatus.values()) {
            if (userStatus.getStatus().equals(status)) {
                return userStatus;
            }
        }
        return null;
    }

    /**
     * 判断用户是否可用
     */
    public boolean isUsable() {
        return this == NORMAL;
    }

    /**
     * 判断是否需要解锁
     */
    public boolean needUnlock() {
        return this == LOCKED || this == FROZEN;
    }

    /**
     * 判断账户是否已注销
     */
    public boolean isCancelled() {
        return this == CANCELLED;
    }

    /**
     * 获取状态描述
     */
    public String getStatusDescription() {
        return this.status + "(" + this.description + ")";
    }

    /**
     * 在JSON序列化时使用status值
     */
    @JsonValue
    public String toValue() {
        return this.status;
    }
}