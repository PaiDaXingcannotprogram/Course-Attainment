package com.go.courseattainment.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-02 15:06
 **/
@Data
public class UpdatePlanForm {

    @ApiModelProperty("课程计划Id")
    private Integer planId;

    @ApiModelProperty("学期")
    private Integer term;

    @ApiModelProperty("课程名称")
    private String courseName;

    @ApiModelProperty("任课教师")
    private String courseTeacher;

}
