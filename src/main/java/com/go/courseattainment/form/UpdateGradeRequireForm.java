package com.go.courseattainment.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-08 10:54
 **/
@Data
public class UpdateGradeRequireForm {


    @ApiModelProperty("一级指标点序号")
    @NotNull(message = "序号不能为空")
    private Integer gradeRequireNo;

    @ApiModelProperty("详细描述")
    private String detailDesc;

    @ApiModelProperty("简要描述")
    private String briefDesc;


}
