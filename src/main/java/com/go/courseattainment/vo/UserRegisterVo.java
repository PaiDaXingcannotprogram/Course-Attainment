package com.go.courseattainment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author: zty
 * @date 2019/10/12 下午7:52
 */
@Data
public class UserRegisterVo {


    @NotNull(message = "用户名不能为空")
    @ApiModelProperty("用户名")
    private String username;

    @NotNull(message = "姓名不能为空")
    @ApiModelProperty("姓名")
    private String name;

    @NotNull(message = "权限不能为空")
    @ApiModelProperty("权限")
    private Integer role;

    @ApiModelProperty("专业，这个可选，如果是专业负责人就要传一个,(0,软工)，（1，计科）")
    private Integer majorId;
}
