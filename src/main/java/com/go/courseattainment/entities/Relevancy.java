package com.go.courseattainment.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-04-07 20:40
 **/

public class Relevancy {


    private Integer relevancyId;

    private String courseName;

    private String detailRequireId;

    private BigDecimal correlation;

    private Integer majorId;

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public Integer getRelevancyId() {
        return relevancyId;
    }

    public void setRelevancyId(Integer relevancyId) {
        this.relevancyId = relevancyId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public String getDetailRequireId() {
        return detailRequireId;
    }

    public void setDetailRequireId(String detailRequireId) {
        this.detailRequireId = detailRequireId == null ? null : detailRequireId.trim();
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
        sb.append(", relevancyId=").append(relevancyId);
        sb.append(", courseName=").append(courseName);
        sb.append(", detailRequireId=").append(detailRequireId);
        sb.append(", correlation=").append(correlation);
        sb.append(" ]");
        return sb.toString();

    }
}
