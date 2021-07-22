package com.go.courseattainment.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-04-17 11:42
 **/
@Data
public class PlanForm {

    @ApiModelProperty("课程名称")
    @NotNull
    private String courseName;

    @ApiModelProperty("学期")
    @NotNull
    private Integer term;

    @ApiModelProperty("任课教师")
    @NotNull
    private String courseTeacher;

    @ApiModelProperty("年级Id")
    @NotNull
    private Integer gradeId;

}
