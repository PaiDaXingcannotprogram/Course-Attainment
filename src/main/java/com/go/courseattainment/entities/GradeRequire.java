package com.go.courseattainment.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: CourseAttainment
 * @description 一级毕业要求
 * @author: 不会编程的派大星
 * @create: 2021-03-25 14:43
 **/

public class GradeRequire implements Serializable {


    private Integer gradeRequireId;

    private Integer gradeRequireNo;

    private String detailDesc;

    private String briefDesc;

    private Integer majorId;

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public Integer getGradeRequireNo() {
        return gradeRequireNo;
    }

    public void setGradeRequireNo(Integer gradeRequireNo) {
        this.gradeRequireNo = gradeRequireNo;
    }

    public Integer getGradeRequireId() {
        return gradeRequireId;
    }

    public void setGradeRequireId(Integer gradeRequireId) {
        this.gradeRequireId = gradeRequireId;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc == null ? null : detailDesc.trim();
    }

    public String getBriefDesc() {
        return briefDesc;
    }

    public void setBriefDesc(String briefDesc) {
        this.briefDesc = briefDesc == null ? null : briefDesc.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
         sb.append(getClass().getSimpleName());
         sb.append(" [");

         sb.append(", gradeRequireId=").append(gradeRequireId);
         sb.append(", gradeRequireNo=").append(gradeRequireNo);
         sb.append(", detailRequire=").append(detailDesc);
         sb.append(", briefDesc=").append(briefDesc);
         sb.append(", majorId=").append(majorId);

         sb.append("]");

         return sb.toString();
    }
}
