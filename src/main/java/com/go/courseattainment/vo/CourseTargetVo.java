package com.go.courseattainment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-16 17:16
 **/
@Data
public class CourseTargetVo {


    @ApiModelProperty("主键PrimaryKey,用于条件查找")
    private Integer courseTargetId;

    @ApiModelProperty("课程目标")
    private String courseTarget;

    @ApiModelProperty("详细描述")
    private String detailDesc;

    @ApiModelProperty("毕业能力")
    private String detailRequireId;

    @ApiModelProperty("显示次序")
    private Integer displayOrder;

}
