package com.go.courseattainment.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: CourseAttainment
 * @description plan课程计划
 *
 * @author: 不会编程的派大星
 * @create: 2021-04-16 19:06
 **/
public class Plan implements Serializable {

    private Integer planId;

    private String courseName;

    private Integer term;

    private String courseTeacher;

    private Integer gradeId;

    private Integer majorId;

    private Integer courseStatus;

    private String computeTime;

    private static final long serialVersionUID = 1L;

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorCourseId(Integer majorId) {
        this.majorId = majorId;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public String getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(String courseTeacher) {
        this.courseTeacher = courseTeacher == null ? null : courseTeacher.trim();
    }

    public Integer getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(Integer courseStatus) {
        this.courseStatus = courseStatus;
    }

    public String getComputeTime() {
        return computeTime;
    }

    public void setComputeTime(String computeTime) {
        this.computeTime = computeTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", planId=").append(planId);
        sb.append(", majorId=").append(majorId);
        sb.append(", term=").append(term);
        sb.append(", courseTeacher=").append(courseTeacher);
        sb.append(", courseStatus=").append(courseStatus);
        sb.append(", computeTime=").append(computeTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

}
