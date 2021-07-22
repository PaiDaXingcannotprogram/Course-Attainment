package com.go.courseattainment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: CourseAttainment
 * @description 登录接收类
 * @author: 不会编程的派大星
 * @create: 2021-03-27 16:48
 **/
@Data
public class LoginVo {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("验证码")
    private String verCode;

    @ApiModelProperty("验证码主键")
    private String verKey;

}
