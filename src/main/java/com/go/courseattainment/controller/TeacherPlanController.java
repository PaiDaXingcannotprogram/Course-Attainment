package com.go.courseattainment.controller;

import com.go.courseattainment.accessctro.RoleContro;
import com.go.courseattainment.enums.RoleEnum;
import com.go.courseattainment.service.TeacherPlanService;
import com.go.courseattainment.vo.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-07-05 19:33
 **/
@RestController
@CrossOrigin
@RequestMapping("/course/TPlan")
@Api(tags = "教师端-教学计划")
@Slf4j
public class TeacherPlanController {


    @Autowired
    private TeacherPlanService teacherPlanService;


    @GetMapping("getPlans")
    @ApiOperation("获取当前登录的老师所对应的教学计划")
    @RoleContro(role = RoleEnum.USER)
    public RespBean getCurrentPlans(){
        return teacherPlanService.getCurrentPlans();
    }



    @PostMapping("/finish")
    @ApiOperation("该教学计划计算完成")
    @RoleContro(role = RoleEnum.USER)
    public RespBean FinishCalculation(Integer planId){
        return teacherPlanService.finishCalculation(planId);
    }

}
