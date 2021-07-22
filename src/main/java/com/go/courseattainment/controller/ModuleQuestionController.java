package com.go.courseattainment.controller;

import com.go.courseattainment.accessctro.RoleContro;
import com.go.courseattainment.enums.RespBeanEnum;
import com.go.courseattainment.enums.RoleEnum;
import com.go.courseattainment.form.ModuleQuestionForm;
import com.go.courseattainment.form.UpdateModuleQuestionForm;
import com.go.courseattainment.service.ModuleQuestionService;
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
 * @create: 2021-06-05 20:37
 **/
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("course/moduleQuestion")
@Api(tags = "教师端-模块小题分支")
public class ModuleQuestionController {

    @Autowired
    private ModuleQuestionService moduleQuestionService;




    @PostMapping("/addQuestion")
    @ApiOperation("添加模块小题")
    @RoleContro(role = RoleEnum.USER)
    public RespBean addModuleQuestion(@Valid ModuleQuestionForm moduleQuestionForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("必填项未填");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return moduleQuestionService.addModuleQuestion(moduleQuestionForm);
    }




    @PostMapping("/deleteQuestion")
    @ApiOperation("删除模块小题")
    @RoleContro(role = RoleEnum.USER)
    public RespBean deleteModuleQuestion(Integer moduleQuestionId){
        if(moduleQuestionId == null){
            log.info("必填项未填");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return moduleQuestionService.deleteModuleQuestion(moduleQuestionId);
    }



    @PostMapping("/updateQuestion")
    @ApiOperation("更新编辑模块小题")
    @RoleContro(role = RoleEnum.USER)
    public RespBean updateModuleQuestion(@Valid UpdateModuleQuestionForm updateModuleQuestionForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("必填项未填");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return moduleQuestionService.updateModuleQuestion(updateModuleQuestionForm);
    }



    @GetMapping("/getAllQuestions")
    @ApiOperation("获取所有排序好的模块小题")
    @RoleContro(role = RoleEnum.USER)
    public RespBean getAllQuestions(){
        return moduleQuestionService.getAllQuestionsByOrder();
    }





    @PostMapping("/getSupportedIds")
    @ApiOperation("获取当前课程所支持的二级指标点")
    @RoleContro(role = RoleEnum.USER)
    public RespBean getCourseSupportedDetailRequireIds(Integer moduleId){
        if(moduleId == null){
            log.info("必填项未填");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return moduleQuestionService.getSupportedDetailRequireIds(moduleId);
    }



}
