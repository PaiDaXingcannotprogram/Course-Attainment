package com.go.courseattainment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-10 21:51
 **/
@Data
public class StudentVo {

    @ApiModelProperty("学号")
    private String stuNo;

    @ApiModelProperty("学生姓名")
    private String stuName;

    @ApiModelProperty("电话号码")
    private String phoneNumber;

    @ApiModelProperty("电子邮箱")
    private String stuEmail;

}
