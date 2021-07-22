package com.go.courseattainment.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-08 12:55
 **/
@Data
public class UpdateDetailRequireForm {

    @ApiModelProperty("二级指标点Id")
    @NotNull(message = "二级指标点不能为空")
    private Integer detailRequireId;


    @ApiModelProperty("二级指标点序号")
    @NotNull
    private String detailRequireNo;

    @ApiModelProperty("详细描述")
    @NotNull
    private String detailDesc;

}
