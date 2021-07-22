package com.go.courseattainment.controller;

import com.go.courseattainment.accessctro.RoleContro;
import com.go.courseattainment.enums.RespBeanEnum;
import com.go.courseattainment.enums.RoleEnum;
import com.go.courseattainment.service.GradeResultsDisplayService;
import com.go.courseattainment.vo.RespBean;
import io.swagger.annotations.Api;
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
 * @create: 2021-07-09 18:33
 **/
@RestController
@RequestMapping("/course/gradeResultsDisplay")
@CrossOrigin
@Api(tags = "专业负责人端-年级数据展示")
@Slf4j
public class GradeResultsDisplayController {

    @Autowired
    private GradeResultsDisplayService gradeResultsDisplayService;



    @GetMapping("/getByMin")
    @ApiOperation("取当前需要展示的整个年级的雷达图数据（最小值）")
    @RoleContro(role = RoleEnum.ADMIN)
    public RespBean getShowingGradeAchievementsByMin(Integer gradeId){
        if(gradeId == null){
            log.info("必填项未填！");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return gradeResultsDisplayService.getShowingGradeRadarMapByMin(gradeId);
    }




    @GetMapping("/getByAverage")
    @ApiOperation("取当前需要展示的整个年级的雷达图数据（平均值）")
    @RoleContro(role = RoleEnum.ADMIN)
    public RespBean getShowingGradeAchievementsByAverage(Integer gradeId){
        if(gradeId == null){
            log.info("必填项未填！");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return gradeResultsDisplayService.getShowingGradeRadarMapByAverage(gradeId);
    }



}
