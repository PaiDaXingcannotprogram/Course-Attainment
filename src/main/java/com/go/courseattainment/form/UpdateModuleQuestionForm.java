package com.go.courseattainment.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-22 10:44
 **/
@Data
public class UpdateModuleQuestionForm {

    @ApiModelProperty("主键PrimaryKey,用于条件查找")
    @NotNull
    private Integer moduleQuestionId;

    @ApiModelProperty("题号")
    @NotNull
    private Integer questionNo;

    @ApiModelProperty("配分")
    @NotNull
    private BigDecimal questionScore;

    @ApiModelProperty("对应的二级指标点")
    @NotNull
    private List<String> detailRequireIds;

    @ApiModelProperty("显示次序")
    @NotNull
    private Integer displayOrder;

}
