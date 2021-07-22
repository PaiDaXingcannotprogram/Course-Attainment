package com.go.courseattainment.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-08 11:07
 **/
@Data
public class SplitDetailRequireForm {

    @ApiModelProperty("二级指标点Id")
    private Integer detailRequireId;

    @ApiModelProperty("详细描述")
    private String detailDesc;


}
