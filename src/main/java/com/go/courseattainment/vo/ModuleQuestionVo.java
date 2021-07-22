package com.go.courseattainment.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-23 10:25
 **/

public class ModuleQuestionVo {

    private Integer moduleQuestionId;

    private Integer questionNo;

    private BigDecimal questionScore;

    private Integer displayOrder;

    private List<String> detailRequireIds;


    public Integer getModuleQuestionId() {
        return moduleQuestionId;
    }

    public void setModuleQuestionId(Integer moduleQuestionId) {
        this.moduleQuestionId = moduleQuestionId;
    }

    public Integer getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(Integer questionNo) {
        this.questionNo = questionNo;
    }

    public BigDecimal getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(BigDecimal questionScore) {
        this.questionScore = questionScore;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public List<String> getDetailRequireIds() {
        return detailRequireIds;
    }

    public void setDetailRequireIds(List<String> detailRequireIds) {
        this.detailRequireIds = detailRequireIds;
    }

    @Override
    public boolean equals(Object obj) {
        ModuleQuestionVo vo = (ModuleQuestionVo) obj;
        if(this.questionNo.equals(vo.questionNo)){
           return true;
       }
       return false;
    }
}
