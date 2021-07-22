package com.go.courseattainment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-04-17 11:42
 **/
@Data
public class PlanVo  {

    @ApiModelProperty("主键priMaryKey,用于条件查找")
    private Integer planId;

    @ApiModelProperty("课程名称")
    private String courseName;

    @ApiModelProperty("学期")
    private Integer term;

    @ApiModelProperty("任课教师")
    private String courseTeacher;

    @ApiModelProperty("计划完成状态")
    private Integer courseStatus;

}
