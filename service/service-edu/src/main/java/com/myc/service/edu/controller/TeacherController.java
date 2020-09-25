package com.myc.service.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myc.common.base.exception.EduException;
import com.myc.common.utils.entity.BaseResponse;
import com.myc.common.utils.entity.ResultCode;
import com.myc.service.edu.entity.Teacher;
import com.myc.service.edu.entity.request.TeacherRequest;
import com.myc.service.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author myc
 * @since 2020-09-23
 */
@Api(tags = "讲师管理")
@RestController
@RequestMapping("/edu/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation("显示所有讲师")
    @GetMapping("/list")
    public BaseResponse listTeachers() {
        List<Teacher> teachers = teacherService.list(null);
        return BaseResponse.ok().data("items", teachers);
    }

    @ApiOperation("逻辑删除")
    @DeleteMapping("/{id}")
    public BaseResponse deleteById(@PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        if (flag) {
            return BaseResponse.ok();
        }
        return BaseResponse.fail();
    }

    @ApiOperation("分页查询")
    @GetMapping("/page/{current}/{limit}")
    public BaseResponse page(@PathVariable Integer current, @PathVariable Integer limit) {
        Page<Teacher> page = new Page<>(current, limit);
        teacherService.page(page, null);

        long total = page.getTotal();
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            throw new EduException(ResultCode.ERROR.getCode(), "有错误");
        }

        List<Teacher> teachers = page.getRecords();
        return BaseResponse.ok().data("total", total).data("rows", teachers);
    }

    @ApiOperation("分页带条件查询")
    @PostMapping("/pageCondition/{current}/{limit}")
    public BaseResponse pageCondition(@PathVariable Integer current, @PathVariable Integer limit,
                                      @RequestBody(required = false) TeacherRequest request) {
        Page<Teacher> page = new Page<>(current, limit);
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        String name = request.getName();
        Integer level = request.getLevel();
        String begin = request.getBegin();
        String end = request.getEnd();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", end);
        }
        teacherService.page(page, wrapper);
        long total = page.getTotal();
        List<Teacher> teachers = page.getRecords();
        return BaseResponse.ok().data("total", total).data("rows", teachers);
    }

    @ApiOperation("添加讲师")
    @PostMapping("/add")
    public BaseResponse add(@RequestBody Teacher teacher) {
        boolean flag = teacherService.save(teacher);
        return flag ? BaseResponse.ok() : BaseResponse.fail();
    }

    @ApiOperation("根据id查询讲师")
    @GetMapping("/get/{id}")
    public BaseResponse get(@PathVariable String id) {
        Teacher teacher = teacherService.getById(id);
        return BaseResponse.ok().data("teacher", teacher);
    }

    @ApiOperation("修改讲师")
    @PutMapping("/{id}")
    public BaseResponse update(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id,
                               @ApiParam(name = "teacher", value = "讲师对象", required = true) @RequestBody Teacher teacher) {
        teacher.setId(id);
        boolean flag = teacherService.updateById(teacher);
        return flag ? BaseResponse.ok() : BaseResponse.fail();
    }
}

