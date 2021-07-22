package com.go.courseattainment.entities;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-02 22:05
 **/
public class ModuleQuestion implements Serializable {

    private Integer moduleQuestionId;

    private Integer moduleId;

    private Integer questionNo;

    private BigDecimal questionScore;

    private String detailRequireId;

    private Integer displayOrder;

    private static final long serialVersionUID = 1L;

    public Integer getModuleQuestionId() {
        return moduleQuestionId;
    }

    public void setModuleQuestionId(Integer moduleQuestionId) {
        this.moduleQuestionId = moduleQuestionId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Integer getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(Integer questionNo) {
        this.questionNo = questionNo;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public BigDecimal getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(BigDecimal questionScore) {
        this.questionScore = questionScore;
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
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", moduleQuestionId=").append(moduleQuestionId);
        sb.append(", moduleId=").append(moduleId);
        sb.append(", questionNo=").append(questionNo);
        sb.append(", questionScore=").append(questionScore);
        sb.append(", detailRequireId=").append(detailRequireId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }


}
