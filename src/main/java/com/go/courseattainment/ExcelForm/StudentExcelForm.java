package com.go.courseattainment.ExcelForm;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-04-15 10:40
 **/
@Data
public class StudentExcelForm extends BaseRowModel implements Serializable {

    @ExcelProperty(value = "学号",index = 0)
    private String stuNo;

    @ExcelProperty(value = "学生姓名",index = 1)
    private String stuName;

    @ExcelProperty(value = "手机号码",index = 2)
    private String phoneNumber;

    @ExcelProperty(value = "电子邮箱",index = 3)
    private String stuEmail;

    @ExcelProperty(value = "专业（软0计1）",index = 4)
    private Integer majorId;

    @ExcelProperty(value = "年级",index = 5)
    private Integer gradeId;

}
