package com.go.courseattainment.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-08 11:13
 **/
@Data
public class DetailRequireForm {

    @ApiModelProperty("对应的一级指标点序号")
    @NotNull
    private Integer gradeRequireNo;

    @ApiModelProperty("二级指标点序号")
    @NotNull
    private String detailRequireNo;

    @ApiModelProperty("详细描述")
    @NotNull
    private String detailDesc;


}
