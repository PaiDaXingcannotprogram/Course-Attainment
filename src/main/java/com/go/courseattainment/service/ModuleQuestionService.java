package com.go.courseattainment.service;

import com.go.courseattainment.form.CalculateModuleSupportedScoreForm;
import com.go.courseattainment.form.ModuleQuestionForm;
import com.go.courseattainment.form.UpdateModuleQuestionForm;
import com.go.courseattainment.vo.RespBean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-05 20:36
 **/
@Service
public interface ModuleQuestionService {

    /**
     * 添加模块小题
     * @param moduleQuestionForm
     * @return RespBean
     */
    RespBean addModuleQuestion(ModuleQuestionForm moduleQuestionForm);


    /**
     * 删除模块小题
     * @param moduleQuestionId
     * @return RespBean
     */
    RespBean deleteModuleQuestion(Integer moduleQuestionId);


    /**
     * 更新、编辑模块小题
     * @param moduleQuestionForm
     * @return RespBean
     */
    RespBean updateModuleQuestion(UpdateModuleQuestionForm moduleQuestionForm);


    /**
     * 获取所有排序好的模块小题
     * @return  RespBean
     */
    RespBean getAllQuestionsByOrder();


    /**
     * 获取可支撑该模块小题的指标点
     * @param moduleId
     * @return  RespBean
     */
    RespBean getSupportedDetailRequireIds(Integer moduleId);


    /**
     * 计算该模块各二级指标点对应的总分数
     * @param moduleId
     * @return BigDecimal
     */
    List<CalculateModuleSupportedScoreForm> CalculateTotalModuleSupportedScore(Integer moduleId);


    /**
     * 根据题号找对应的二级指标点
     * @param questionNo
     * @return String
     */
    String getDetailIdByNo(Integer questionNo);

}
