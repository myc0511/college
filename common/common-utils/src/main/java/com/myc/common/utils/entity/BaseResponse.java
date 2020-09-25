package com.myc.common.utils.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author myc
 * @desc BaseResponse
 * @date 2020-09-24
 */
@Data
public class BaseResponse {

    @ApiModelProperty("是否成功")
    private Boolean success;

    @ApiModelProperty("返回码")
    private Integer code;

    @ApiModelProperty("返回消息")
    private String message;

    @ApiModelProperty("返回数据")
    private Map<String, Object> data = new HashMap<>();

    private BaseResponse() {

    }

    public static BaseResponse ok() {
        BaseResponse response = new BaseResponse();
        response.setSuccess(true);
        response.setCode(ResultCode.SUCCESS.getCode());
        response.setMessage("success");
        return response;
    }

    public static BaseResponse fail() {
        BaseResponse response = new BaseResponse();
        response.setSuccess(false);
        response.setCode(ResultCode.ERROR.getCode());
        response.setMessage("error");
        return response;
    }

    public BaseResponse success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public BaseResponse code(Integer code) {
        this.setCode(code);
        return this;
    }

    public BaseResponse message(String message) {
        this.setMessage(message);
        return this;
    }

    public BaseResponse data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public BaseResponse data(Map<String, Object> data) {
        this.setData(data);
        return this;
    }
}
