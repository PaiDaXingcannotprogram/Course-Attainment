package com.go.courseattainment.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-02 15:26
 **/
public class
CourseTarget implements Serializable {

    private Integer courseTargetId;

    private String courseTarget;

    private String detailDesc;

    private String detailRequireId;

    private Integer displayOrder;

    public Integer getCourseTargetId() {
        return courseTargetId;
    }

    public void setCourseTargetId(Integer courseTargetId) {
        this.courseTargetId = courseTargetId;
    }

    public String getCourseTarget() {
        return courseTarget;
    }

    public void setCourseTarget(String courseTarget) {
        this.courseTarget = courseTarget == null ? null : courseTarget.trim();
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc == null ? null : detailDesc.trim();
    }

    public String getDetailRequireId() {
        return detailRequireId;
    }

    public void setDetailRequireId(String detailRequireId) {
        this.detailRequireId = detailRequireId == null ? null : detailRequireId.trim();
    }


    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(getClass().getSimpleName());

        stringBuilder.append(" [");

        stringBuilder.append(" Hash=").append(hashCode());
        stringBuilder.append(", courseTargetId=").append(courseTargetId);
        stringBuilder.append(", courseTarget=").append(courseTarget);
        stringBuilder.append(", detailDesc=").append(detailDesc);
        stringBuilder.append(", detailRequireId=").append(detailRequireId);
        stringBuilder.append(", displayOrder=").append(displayOrder);
        stringBuilder.append("]");
        return stringBuilder.toString();


    }
}
