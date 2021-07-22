package com.go.courseattainment.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-04-15 15:55
 **/
@Data
public class StudentForm {

    @ApiModelProperty("学号")
    @NotNull(message = "学号不能为空")
    private String stuNo;

    @ApiModelProperty("学生姓名")
    @NotNull(message = "学生姓名")
    private String stuName;

    @ApiModelProperty("手机号码")
    @NotNull(message = "手机号码不能为空")
    private String phoneNumber;

    @ApiModelProperty("电子邮箱")
    @NotNull(message = "电子邮箱不能为空")
    private String stuEmail;

    @ApiModelProperty("年级")
    @NotNull(message = "年级ID不能为空")
    private String gradeId;
}
