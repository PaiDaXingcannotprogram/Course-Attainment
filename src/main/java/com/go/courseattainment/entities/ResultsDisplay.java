package com.go.courseattainment.entities;

import sun.security.provider.certpath.PKIXTimestampParameters;

import javax.swing.*;
import java.math.BigDecimal;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-07-06 09:40
 **/
public class ResultsDisplay {


    private Integer resultsDisplayId;

    private Integer stuId;

    private String stuNo;

    private String stuName;

    private String detailRequireId;

    private String courseName;

    private BigDecimal stuAchievement;

    private BigDecimal correlation;

    public Integer getResultsDisplayId() {
        return resultsDisplayId;
    }

    public void setResultsDisplayId(Integer resultsDisplayId) {
        this.resultsDisplayId = resultsDisplayId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo == null ? null : stuNo.trim();
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName == null ? null : stuName.trim();
    }

    public String getDetailRequireId() {
        return detailRequireId;
    }

    public void setDetailRequireId(String detailRequireId) {
        this.detailRequireId = detailRequireId == null ? null : detailRequireId.trim();
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public BigDecimal getStuAchievement() {
        return stuAchievement;
    }

    public void setStuAchievement(BigDecimal stuAchievement) {
        this.stuAchievement = stuAchievement;
    }

    public BigDecimal getCorrelation() {
        return correlation;
    }

    public void setCorrelation(BigDecimal correlation) {
        this.correlation = correlation;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", resultsDisplayId=").append(resultsDisplayId);
        sb.append(", stuId=").append(stuId);
        sb.append(", stuNo=").append(stuNo);
        sb.append(", stuName=").append(stuName);
        sb.append(", courseName=").append(courseName);
        sb.append(", detailRequireId=").append(detailRequireId);
        sb.append(", stuAchievement=").append(stuAchievement);
        sb.append(", correlation=").append(correlation);
        sb.append(" ]");
        return sb.toString();
    }
}
