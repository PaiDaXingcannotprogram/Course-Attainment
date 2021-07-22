package com.go.courseattainment.vo;

import lombok.Data;

import java.util.Date;

/**
 * @program: CourseAttainment
 * @description 教师端课程信息
 * @author: 不会编程的派大星
 * @create: 2021-04-14 20:11
 **/
@Data
public class CourseInfoVo {

    private Integer grade;

    private String majorName;

    private  String courseName;

    private Integer taskStatus;

    private Date date;
}
