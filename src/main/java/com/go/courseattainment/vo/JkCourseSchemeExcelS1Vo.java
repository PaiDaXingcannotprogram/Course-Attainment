package com.go.courseattainment.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
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
public class JkCourseSchemeExcelS1Vo extends BaseRowModel {


    @ExcelProperty(value = {"附表四：计算机科学与技术专业课程设置表","课程号","课程号"},index = 4)
    @ApiModelProperty("课程号")
    private String courseId;

    @ExcelProperty(value = {"附表四：计算机科学与技术专业课程设置表","课程名","课程名"},index = 5)
    @ApiModelProperty("课程名")
    private String courseName;

    @ExcelProperty(value = {"附表四：计算机科学与技术专业课程设置表","英文课程名","英文课程名"},index = 6)
    @ApiModelProperty("英文课程名")
    private String courseEnName;

    @ExcelProperty(value = {"附表四：计算机科学与技术专业课程设置表","学分","学分"},index = 7)
    @ApiModelProperty( value = "学分",example = "0.0")
    private BigDecimal credit;

    /**
    @ExcelProperty(value = {"修读要求","修读要求"},index = 21)
    @ApiModelProperty("修读要求")
    private BigDecimal courseRequirement;
    */

}



