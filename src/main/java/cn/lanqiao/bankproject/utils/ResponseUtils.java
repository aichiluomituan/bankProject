package cn.lanqiao.bankproject.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 响应类
@Data
public class ResponseUtils<T> {
    // 响应状态码
    private int code;

    // 响应信息
    private String msg;

    // 响应数据
    private T data;

    // 无参构造
    public ResponseUtils() {
    }

    // 构造方法重载
    public ResponseUtils(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public ResponseUtils(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // 静态成功方法
    public static <T> ResponseUtils<T> success(String msg) {
        return new ResponseUtils<>(200, msg);
    }

    public static <T> ResponseUtils<T> success(String msg, T data) {
        return new ResponseUtils<>(200, msg, data);
    }

    // 静态失败方法
    public static <T> ResponseUtils<T> error(int code, String msg) {
        return new ResponseUtils<>(code, msg);
    }

    // getter和setter方法
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}