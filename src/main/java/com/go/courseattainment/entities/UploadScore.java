package com.go.courseattainment.entities;

import org.omg.CORBA.StringHolder;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-24 19:25
 **/
public class UploadScore implements Serializable {

    private Integer uploadScoreId;

    private Integer moduleId;

    private String stuNo;

    private String stuName;

    private BigDecimal moduleScore;

    private String detailRequireId;

    private Integer stuStatus;


    public Integer getUploadScoreId() {
        return uploadScoreId;
    }

    public void setUploadScoreId(Integer uploadScoreId) {
        this.uploadScoreId = uploadScoreId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
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

    public BigDecimal getModuleScore() {
        return moduleScore;
    }

    public void setModuleScore(BigDecimal moduleScore) {
        this.moduleScore = moduleScore;
    }

    public Integer getStuStatus() {
        return stuStatus;
    }

    public void setStuStatus(Integer stuStatus) {
        this.stuStatus = stuStatus;
    }


    public String getDetailRequireId() {
        return detailRequireId;
    }

    public void setDetailRequireId(String detailRequireId) {
        this.detailRequireId = detailRequireId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append(", uploadScoreId=").append(uploadScoreId);
        sb.append(", moduleId=").append(moduleId);
        sb.append(", stuNo=").append(stuNo);
        sb.append(", stuName=").append(stuName);
        sb.append(", moduleScore=").append(moduleScore);
        sb.append(", moduleScore=").append(moduleScore);
        sb.append(", detailRequireId=").append(detailRequireId);
        sb.append("]");
        return sb.toString();
    }
}
