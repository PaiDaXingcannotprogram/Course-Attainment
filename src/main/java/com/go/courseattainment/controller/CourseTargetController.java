package com.go.courseattainment.controller;

import com.go.courseattainment.accessctro.RoleContro;
import com.go.courseattainment.enums.RespBeanEnum;
import com.go.courseattainment.enums.RoleEnum;
import com.go.courseattainment.form.CourseTargetForm;
import com.go.courseattainment.form.UpdateCourseTargetForm;
import com.go.courseattainment.service.CourseTargetService;
import com.go.courseattainment.vo.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-02 15:31
 **/

@RestController
@CrossOrigin
@RequestMapping("course/courseTarget")
@Slf4j
@Api(tags = "教师端-课程目标")
public class CourseTargetController {


    @Autowired
    private CourseTargetService courseTargetService;


    @PostMapping("/addTarget")
    @ApiOperation("添加课程目标")
    @RoleContro(role = RoleEnum.USER)
    public RespBean addCourseTarget(@Valid CourseTargetForm courseTargetForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("必填项未填");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return courseTargetService.addCourseTarget(courseTargetForm);
    }



    @PostMapping("/updateTarget")
    @ApiOperation("编辑课程目标")
    @RoleContro(role = RoleEnum.USER)
    public RespBean updateCourseTarget(@Valid UpdateCourseTargetForm updateCourseTargetForm,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("必填项未填");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return courseTargetService.updateCourseTarget(updateCourseTargetForm);
    }


    @PostMapping("/deleteTarget")
    @ApiOperation("删除课程目标")
    @ApiImplicitParam(name = "courseTargetId",value = "主键PrimaryKey，用于条件查找")
    @RoleContro(role = RoleEnum.USER)
    public RespBean deleteCourseTarget(Integer courseTargetId){
        if(courseTargetId == null){
            log.info("必填项未填");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return courseTargetService.deleteCourseTarget(courseTargetId);
    }



    @GetMapping("/getAllTargets")
    @ApiOperation("获取所有排序后的课程目标")
    @RoleContro(role = RoleEnum.USER)
    public RespBean getAllTargetsByOrder(){
        return courseTargetService.getAllTargetsByOrder();
    }


}
