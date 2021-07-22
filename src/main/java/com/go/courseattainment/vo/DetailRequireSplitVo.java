package com.go.courseattainment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-10 13:28
 **/
@Data
public class DetailRequireSplitVo {

    @ApiModelProperty("二级指标点PrimaryKey，用于单个查找")
    private Integer detailRequireId;

    @ApiModelProperty("二级指标点序号")
    private String detailRequireNo;

    @ApiModelProperty("详细描述")
    private String detailDesc;

}
