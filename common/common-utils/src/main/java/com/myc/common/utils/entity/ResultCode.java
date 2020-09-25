package com.myc.common.utils.entity;

import lombok.Data;

/**
 * @author myc
 * @desc ResultCode
 * @date 2020-09-24
 */
public enum ResultCode {
    /**
     * 成功
     */
    SUCCESS(200),
    /**
     * 失败
     */
    ERROR(201);

    private Integer code;

    ResultCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
