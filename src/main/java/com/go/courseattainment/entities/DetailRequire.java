package com.go.courseattainment.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @program: CourseAttainment
 * @description 二级=毕业要求
 * @author: 不会编程的派大星
 * @create: 2021-03-25 14:46
 **/
public class DetailRequire implements Serializable {

    private Integer detailRequireId;

    private String detailRequireNo;

    private Integer gradeRequireNo;

    private String detailDesc;

    private Integer majorId;





    public Integer getDetailRequireId() {
        return detailRequireId;
    }

    public void setDetailRequireId(Integer detailRequireId) {
        this.detailRequireId = detailRequireId;
    }

    public String getDetailRequireNo() {
        return detailRequireNo;
    }

    public void setDetailRequireNo(String detailRequireNo) {
        this.detailRequireNo = detailRequireNo == null ? null : detailRequireNo.trim();
    }

    public Integer getGradeRequireNo() {
        return gradeRequireNo;
    }

    public void setGradeRequireNo(Integer gradeRequireNo) {
        this.gradeRequireNo = gradeRequireNo;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc == null ? null : detailDesc.trim();
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(getClass().getSimpleName());
        sb.append(" [");

        sb.append("Hash=").append(hashCode());
        sb.append(", detailRequireId=").append(detailRequireId);
        sb.append(", detailRequireNo=").append(detailRequireNo);
        sb.append(", gradeRequireNo=").append(gradeRequireNo);
        sb.append(", detailDesc=").append(detailDesc);
        sb.append(", majorId=").append(majorId);

        sb.append("]");

        return sb.toString();

    }
}
