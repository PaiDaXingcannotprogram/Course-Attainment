package com.go.courseattainment.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-04-07 20:45
 **/
@Data
public class RelevancyVo {

    @ApiModelProperty("课程名")
    @NotNull(message = "课程名不能为空")
    private String courseName;

    @ApiModelProperty("二级指标点Id")
    @NotNull(message = "二级指标点id不能为空")
    private String detailRequireId;

    @ApiModelProperty("关联度")
    @NotNull(message = "关联度不能为空")
    private BigDecimal correlation;

}
