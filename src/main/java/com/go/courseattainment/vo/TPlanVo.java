package com.go.courseattainment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.util.Date;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-07-05 20:08
 **/
@Data
public class TPlanVo {

    @ApiModelProperty("年级")
    private Integer gradeId;

    @ApiModelProperty("专业名称，0软工，1计科")
    private String majorId;

    @ApiModelProperty("课程名称")
    private String courseName;

    @ApiModelProperty("课程状态")
    private Integer courseStatus;

    @ApiModelProperty("完成时间")
    private String computeTime;
}
