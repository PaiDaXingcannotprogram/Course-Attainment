package com.go.courseattainment.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-07-05 18:15
 **/
@Data
public class UpdateModuleForm {

    @ApiModelProperty("主键PrimaryKey，用于条件查找")
    private Integer moduleId;

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
