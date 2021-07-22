package com.go.courseattainment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashMap;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-07-05 14:48
 **/
@Data
public class UploadScoreVo {

    @ApiModelProperty("当前上分的学生的前面的序号")
    private Integer uploadScoreId;

    @ApiModelProperty("模块Id")
    private Integer moduleId;

    @ApiModelProperty("学号")
    private String stuNo;

    @ApiModelProperty("学生姓名")
    private String stuName;

    @ApiModelProperty("学生得分")
    private HashMap<Integer, BigDecimal> stuScore;

    @ApiModelProperty("考试状态备注（0异常 1正常）")
    private Integer stuStatusNotes;

}
