package com.go.courseattainment.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-20 18:34
 **/
@Data
public class ModuleForm {

    @ApiModelProperty("该模块对应的教学计划Id")
    @NotNull
    private Integer planId;

    @ApiModelProperty("该模块所属的课程名称")
    @NotNull
    private String courseName;

    @ApiModelProperty("模块类型（名称）")
    @NotNull
    private String moduleType;

    @ApiModelProperty("模块占比")
    @NotNull
    private BigDecimal courseProportion;


}
