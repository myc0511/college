package com.myc.common.base.handler;

import com.myc.common.base.exception.EduException;
import com.myc.common.utils.entity.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author myc
 * @desc GlobalExceptionHandler
 * @date 2020-09-25
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 全局异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResponse error(Exception e) {
        e.printStackTrace();
        return BaseResponse.fail().message(e.getMessage());
    }

    /**
     * 特定异常
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public BaseResponse error(ArithmeticException e) {
        e.printStackTrace();
        return BaseResponse.fail().message(e.getMessage());
    }

    /**
     * 自定义异常
     */
    @ExceptionHandler(EduException.class)
    @ResponseBody
    public BaseResponse error(EduException e) {
        e.printStackTrace();
        log.error(e.getMsg());
        return BaseResponse.fail().code(e.getCode()).message(e.getMsg());
    }
}
