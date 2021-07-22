package com.go.courseattainment.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-16 16:40
 **/
@Data
public class CourseTargetForm {

    @ApiModelProperty("课程目标")
    @NotNull
    private String courseTarget;

    @ApiModelProperty("详细描述")
    @NotNull
    private String detailDesc;

    @ApiModelProperty("毕业能力")
    @NotNull
    private String detailRequireId;

    @ApiModelProperty("显示次序")
    @NotNull
    private Integer displayOrder;

}
