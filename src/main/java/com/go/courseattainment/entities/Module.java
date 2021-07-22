package com.go.courseattainment.entities;

import java.math.BigDecimal;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-17 13:44
 **/
public class Module {

    private Integer planId;

    private String courseName;

    private Integer moduleId;

    private String moduleType;

    private BigDecimal courseProportion;


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType == null ? null : moduleType.trim();
    }

    public BigDecimal getCourseProportion() {
        return courseProportion;
    }

    public void setCourseProportion(BigDecimal courseProportion) {
        this.courseProportion = courseProportion;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash=").append(hashCode());
        sb.append(", courseName=").append(courseName);
        sb.append(", moduleId=").append(moduleId);
        sb.append(", moduleType=").append(moduleType);
        sb.append(", courseProportion").append(courseProportion);
        sb.append(", planId").append(planId);
        sb.append("]");
        return sb.toString();
    }
}
