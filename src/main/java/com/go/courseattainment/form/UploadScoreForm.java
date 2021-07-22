package com.go.courseattainment.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashMap;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-24 21:30
 **/
@Data
public class UploadScoreForm {

    @ApiModelProperty("当前上分的学生的前面的序号")
    @NotNull
    private Integer uploadScoreId;

    @ApiModelProperty("模块Id")
    @NotNull
    private Integer moduleId;

    @ApiModelProperty("学号")
    @NotNull
    private String stuNo;

    @ApiModelProperty("学生姓名")
    @NotNull
    private String stuName;

    @ApiModelProperty("学生得分")
    @NotNull
    private HashMap<Integer, BigDecimal> stuScore;

    @ApiModelProperty("考试状态备注（0异常 1正常）")
    @NotNull
    private Integer stuStatusNotes;

}
