package com.go.courseattainment.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-27 20:41
 **/
@Data
public class CalculateModuleSupportedScoreForm {

    @ApiModelProperty("二级指标点")
    private String detailRequireId;

    @ApiModelProperty("该模块二级指标点支持的全部分数")
    private BigDecimal detailRequireIdTotalScore;


}
