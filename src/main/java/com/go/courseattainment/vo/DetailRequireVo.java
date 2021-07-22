package com.go.courseattainment.vo;

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
public class DetailRequireVo {

    @ApiModelProperty("二级指标点PrimaryKey，用于单个查找")
    private Integer detailRequireId;

    @ApiModelProperty("二级指标点序号")
    private String detailRequireNo;

    @ApiModelProperty("详细描述")
    private String detailDesc;

}
