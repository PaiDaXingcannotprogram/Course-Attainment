package com.go.courseattainment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-04-08 11:37
 **/
@Data
public class CourseSchemeVo {

    @ApiModelProperty("课程号")
    private String courseId;

    @ApiModelProperty("课程名")
    private String courseName;

    @ApiModelProperty("学分")
    private BigDecimal credit;
}
