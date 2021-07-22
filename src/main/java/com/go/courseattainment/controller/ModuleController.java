package com.go.courseattainment.controller;

import com.go.courseattainment.accessctro.RoleContro;
import com.go.courseattainment.enums.RespBeanEnum;
import com.go.courseattainment.enums.RoleEnum;
import com.go.courseattainment.form.ModuleForm;
import com.go.courseattainment.form.UpdateModuleForm;
import com.go.courseattainment.service.ModuleService;
import com.go.courseattainment.vo.RespBean;
import io.swagger.annotations.Api;
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
 * @create: 2021-06-20 18:31
 **/
@RestController
@CrossOrigin
@RequestMapping("/course/module")
@Slf4j
@Api(tags = "教师端-课程模块")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;


    @PostMapping("/addModule")
    @ApiOperation("添加课程模块")
    @RoleContro(role = RoleEnum.USER)
    public RespBean addModule(@Valid ModuleForm moduleForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("必填项未填！");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return moduleService.addModule(moduleForm);
    }



    @GetMapping("delete")
    @ApiOperation("删除课程下的模块")
    @RoleContro(role = RoleEnum.USER)
    public RespBean deleteModule(Integer moduleId){
        if(moduleId == null){
            log.info("必填项未填！");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return moduleService.deleteModule(moduleId);
    }




    @PostMapping("/update")
    @ApiOperation("更新、编辑课程下的模块")
    @RoleContro(role = RoleEnum.USER)
    public RespBean updateModule(@Valid UpdateModuleForm updateModuleForm,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("必填项未填！");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return moduleService.updateModule(updateModuleForm);
    }



    @GetMapping("getAll")
    @ApiOperation("获取所有该教学计划下的模块")
    @RoleContro(role = RoleEnum.USER)
    public RespBean getAllCourseModules(Integer planId){
        if(planId == null){
            log.info("必填项未填！");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return moduleService.getAllCourseModules(planId);
    }



}
