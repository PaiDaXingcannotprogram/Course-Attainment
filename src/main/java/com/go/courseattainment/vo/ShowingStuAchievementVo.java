package com.go.courseattainment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-07-08 20:43
 **/
@Data
public class ShowingStuAchievementVo {

    @ApiModelProperty("指标点")
    private String detailRequireId;

    @ApiModelProperty("课程名称")
    private String courseName;

    @ApiModelProperty("课程达成度")
    private BigDecimal stuAchievement;

    @ApiModelProperty("占比")
    private BigDecimal correlation;
}
