package com.go.courseattainment.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-10 21:34
 **/
@Data
public class UpdateStudentForm {

    @ApiModelProperty("主键primaryKey")
    @NotNull
    private Integer stuId;

    @ApiModelProperty("学号")
    @NotNull
    private String stuNo;

    @ApiModelProperty("学生姓名")
    @NotNull
    private String stuName;

    @ApiModelProperty("手机号码")
    @NotNull
    private String phoneNumber;

    @ApiModelProperty("电子邮箱")
    @NotNull
    private String stuEmail;


}
