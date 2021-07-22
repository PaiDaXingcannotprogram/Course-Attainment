package com.go.courseattainment.controller;

import com.go.courseattainment.accessctro.RoleContro;
import com.go.courseattainment.enums.RespBeanEnum;
import com.go.courseattainment.enums.RoleEnum;
import com.go.courseattainment.service.StuResultsDisplayService;
import com.go.courseattainment.vo.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-07-08 21:22
 **/
@RestController
@RequestMapping("/course/stuResultsDisplay")
@CrossOrigin
@Api(tags = "专业负责人端-单个学生数据展示")
@Slf4j
public class StuResultsDisplayController {

    @Autowired
    private StuResultsDisplayService stuResultsDisplayService;


    @GetMapping("/getInfo")
    @ApiOperation("获取当年需要展示数据的学生信息")
    @RoleContro(role = RoleEnum.ADMIN)
    public RespBean getShowingStuInfos(Integer gradeId){
        if(gradeId == null){
            log.info("必填项未填！");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return stuResultsDisplayService.getShowingStuInfo(gradeId);
    }



    @GetMapping("/getByNo")
    @ApiOperation("根据学号获取要展示数据的学生信息")
    @RoleContro(role = RoleEnum.ADMIN)
    public RespBean getShowingStuInfoByNo(String stuNo){
        if(stuNo.isEmpty()){
            log.info("必填项未填！");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return stuResultsDisplayService.getShowingStuInfoByNo(stuNo);
    }





    @GetMapping("/getByName")
    @ApiOperation("根据姓名获取要展示数据的学生信息")
    @RoleContro(role = RoleEnum.ADMIN)
    public RespBean getShowingStuInfoByName(String stuName){
        if(stuName.isEmpty()){
            log.info("必填项未填！");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return stuResultsDisplayService.getShowingStuInfoByName(stuName);
    }




    @GetMapping("/getAchievement")
    @ApiOperation("获取当前需要展示的学生的达成度信息数据")
    @RoleContro(role = RoleEnum.ADMIN)
    public RespBean getShowingStuAchievement(Integer stuId){
        if(stuId == null){
            log.info("必填项未填！");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return stuResultsDisplayService.getShowingStuAchievement(stuId);
    }





    @GetMapping("/getRadarMapByMin")
    @ApiOperation("获取当前需要展示的学生的雷达图数据（最小值）")
    @RoleContro(role = RoleEnum.ADMIN)
    public RespBean getShowingStuRadarMapByMin(Integer stuId){
        if(stuId == null){
            log.info("必填项未填！");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return stuResultsDisplayService.getShowingStuRadarMapByMin(stuId);
    }




    @GetMapping("/getRadarMapByAver")
    @ApiOperation("获取当前需要展示的学生的雷达图数据（平均值）")
    @RoleContro(role = RoleEnum.ADMIN)
    public RespBean getShowingStuRadarMapByAverage(Integer stuId){
        if(stuId == null){
            log.info("必填项未填！");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return stuResultsDisplayService.getShowingStuRadarMapByAverage(stuId);
    }



}
