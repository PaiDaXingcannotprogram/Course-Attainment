package com.go.courseattainment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-10 15:23
 **/
@Data
public class GradeRequireVo {

    @ApiModelProperty("一级指标点序号")
    private Integer gradeRequireNo;

    @ApiModelProperty("详细描述")
    private String detailDesc;

    @ApiModelProperty("简要描述")
    private String briefDesc;

}
