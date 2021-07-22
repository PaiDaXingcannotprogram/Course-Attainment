package com.go.courseattainment.entities;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-04-02 17:01
 **/
@Data
public class CourseScheme {

    @ApiModelProperty("课程号")
    private String courseId;


    @ApiModelProperty("课程名")
    private String courseName;


    @ApiModelProperty("英文课程名")
    private String courseEnName;


    @ApiModelProperty( "学分")
    private BigDecimal credit;



    @ApiModelProperty("修读要求")
    private BigDecimal courseRequirement;


    /**
    @ApiModelProperty( "核心课程")
    private Integer coreCourse;


    @ApiModelProperty("跨学科选修课程")
    private Integer  interCourse;
     */

    @ApiModelProperty("专业名称")
    private Integer majorId;

}



