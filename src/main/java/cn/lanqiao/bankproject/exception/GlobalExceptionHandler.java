package cn.lanqiao.bankproject.exception;/*
@author 比巴卜
@date  2025/1/1 下午3:39
@Description 全局异常处理器
*/

import cn.lanqiao.bankproject.utils.ResponseUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseUtils<Void> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResponseUtils.error(400, message);
    }

    @ExceptionHandler(Exception.class)
    public ResponseUtils<Void> handleAllExceptions(Exception ex) {
        return ResponseUtils.error(500, ex.getMessage());
    }
}