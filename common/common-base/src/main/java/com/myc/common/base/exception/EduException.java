package com.myc.common.base.exception;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author myc
 * @desc EduException
 * @date 2020-09-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EduException extends RuntimeException {
    @ApiModelProperty("状态码")
    private Integer code;
    @ApiModelProperty("异常信息")
    private String msg;
}
