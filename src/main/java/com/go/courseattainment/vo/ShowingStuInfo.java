package com.go.courseattainment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-07-08 20:30
 **/
@Data
public class ShowingStuInfo {

    @ApiModelProperty("学生编号")
    private Integer stuId;

    @ApiModelProperty("年级")
    private Integer gradeId;

    @ApiModelProperty("专业名称")
    private Integer majorId;

    @ApiModelProperty("学号")
    private String stuNo;

    @ApiModelProperty("姓名")
    private String stuName;

}
