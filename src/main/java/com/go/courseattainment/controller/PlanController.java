package com.go.courseattainment.controller;

import com.go.courseattainment.accessctro.RoleContro;
import com.go.courseattainment.enums.RespBeanEnum;
import com.go.courseattainment.enums.RoleEnum;
import com.go.courseattainment.form.PlanForm;
import com.go.courseattainment.form.UpdatePlanForm;
import com.go.courseattainment.service.PlanService;
import com.go.courseattainment.service.UserService;
import com.go.courseattainment.vo.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-04-16 19:46
 **/
@Controller
@RequestMapping("course/plan")
@CrossOrigin
@Slf4j
@Api(tags = "专业负责人端-教学计划")
public class PlanController {

    @Autowired
    private UserService userService;

    @Autowired
    private PlanService planService;


    @RequestMapping(value = "/getTeachers",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("获取所有任课老师")
    @RoleContro(role = RoleEnum.ADMIN)
    public RespBean getAllCourseTeachers(){
         return userService.getAllCourseTeacherNames();
    }




    @PostMapping("/addPlan")
    @ApiOperation("添加教学计划")
    @RoleContro(role = RoleEnum.ADMIN)
    public RespBean addTeachPlan(@Valid PlanForm planForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("必填项未填！");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return planService.addTeachPlan(planForm);
    }



    @PostMapping("/deletePlan")
    @ApiOperation("删除教学计划")
    @RoleContro(role = RoleEnum.ADMIN)
    @ApiImplicitParam(name = "planId",value = "主键primaryKey,用于条件查找")
    public RespBean deletePlan(Integer planId){
        if(planId ==null){
            log.info("必填项未填");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return planService.deleteTeachPlan(planId);
    }



    @PostMapping("/updatePlan")
    @ApiOperation("更新、编辑教学计划")
    @RoleContro(role = RoleEnum.ADMIN)
    public RespBean updatePlan(@Valid UpdatePlanForm updatePlanForm,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("必填项未填！");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return planService.updateTeachPlan(updatePlanForm);
    }



    @GetMapping("/getAllPlans")
    @ApiOperation("获取所有教学计划")
    @RoleContro(role = RoleEnum.ADMIN)
    public RespBean getAllPlans(){
        return planService.getAllPlans();
    }



}
