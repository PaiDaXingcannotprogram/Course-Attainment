package com.go.courseattainment.controller;

import com.go.courseattainment.accessctro.RoleContro;
import com.go.courseattainment.enums.RespBeanEnum;
import com.go.courseattainment.enums.RoleEnum;
import com.go.courseattainment.form.RelevancyForm;
import com.go.courseattainment.service.RelevancyService;
import com.go.courseattainment.vo.CourseSchemeVo;
import com.go.courseattainment.vo.RelevancyVo;
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
import java.util.List;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-04-07 21:09
 **/
@RestController
@RequestMapping("course/relevancy")
@CrossOrigin
@Slf4j
@Api(tags = "专业负责人端-矩阵管理-关联度设置")
public class RelevancyController {

    @Autowired
    private RelevancyService relevancyService;




    @GetMapping("/getCourseInfos")
    @ApiOperation("获取所有专业课程信息")
    @RoleContro(role = RoleEnum.ADMIN)
    public RespBean getAllCourseInfos(){
        return relevancyService.getAllCourseInfos();
    }



    @GetMapping("getAllDetailIds")
    @ApiOperation("获取所有二级指标点")
    @RoleContro(role = RoleEnum.ADMIN)
    public RespBean getAllDetailIds(){
        return relevancyService.getAllDetailNos();
    }




    @PostMapping("/addOneCourseInfo")
    @ApiOperation("添加单个课程信息")
    @RoleContro(role = RoleEnum.ADMIN)
    public RespBean addOneCourseInfo(@Valid CourseSchemeVo courseSchemeVo,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("必填项未填");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return relevancyService.addOneCourseInfo(courseSchemeVo);
    }



    @PostMapping("/setRelevancy")
    @ApiOperation("设置课程与二级达标点之间的关联度")
    @RoleContro(role = RoleEnum.ADMIN)
    @ApiImplicitParam(name = "courseRelevancyList",value = "课程姓名")
    public RespBean setCourseRelevancy(List<RelevancyForm> courseRelevancyList){
        if(courseRelevancyList.isEmpty()){
            log.info("必填项未填");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return relevancyService.setCourseRelevancy(courseRelevancyList);
    }





}