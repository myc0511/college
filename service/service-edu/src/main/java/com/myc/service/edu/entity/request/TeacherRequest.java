package com.myc.service.edu.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author myc
 * @desc TeacherRequest
 * @date 2020-09-24
 */
@Data
public class TeacherRequest {
    @ApiModelProperty("教师名称，模糊查询")
    private String name;

    @ApiModelProperty("教师级别，1 高级讲师 2 特级讲师")
    private Integer level;

    @ApiModelProperty("查询开始时间，格式:2020-11-11 11:11:22")
    private String begin;

    @ApiModelProperty("查询结束时间，格式:2020-11-11 11:11:22")
    private String end;
}
