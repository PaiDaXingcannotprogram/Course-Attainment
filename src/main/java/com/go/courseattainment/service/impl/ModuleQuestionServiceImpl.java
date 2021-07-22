package com.go.courseattainment.service.impl;

import com.go.courseattainment.entities.Module;
import com.go.courseattainment.entities.ModuleQuestion;
import com.go.courseattainment.entities.Relevancy;
import com.go.courseattainment.enums.RespBeanEnum;
import com.go.courseattainment.form.CalculateModuleSupportedScoreForm;
import com.go.courseattainment.form.ModuleQuestionForm;
import com.go.courseattainment.form.UpdateModuleQuestionForm;
import com.go.courseattainment.mapper.ModuleMapper;
import com.go.courseattainment.mapper.ModuleQuestionMapper;
import com.go.courseattainment.mapper.RelevancyMapper;
import com.go.courseattainment.service.ModuleQuestionService;
import com.go.courseattainment.vo.ModuleQuestionVo;
import com.go.courseattainment.vo.RespBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-05 20:36
 **/
@Service
public class ModuleQuestionServiceImpl implements ModuleQuestionService {

    @Autowired
    private ModuleQuestionMapper moduleQuestionMapper;

    @Autowired
    private ModuleMapper moduleMapper;

    @Autowired
    private RelevancyMapper relevancyMapper;

    @Override
    public RespBean addModuleQuestion(ModuleQuestionForm moduleQuestionForm) {
        ModuleQuestion moduleQuestion = new ModuleQuestion();
        BeanUtils.copyProperties(moduleQuestionForm,moduleQuestion);
        int result = moduleQuestionMapper.insertIntoQuestion(moduleQuestion);
        if(result != 0){
            return RespBean.SUCCESS("添加成功");
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }


    @Override
    public RespBean deleteModuleQuestion(Integer moduleQuestionId) {
        int result = moduleQuestionMapper.deleteFromQuestion(moduleQuestionId);
        if(result != 0){
            return RespBean.SUCCESS("删除成功");
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }

    @Override
    public RespBean updateModuleQuestion(UpdateModuleQuestionForm updateModuleQuestionForm) {
        ModuleQuestion moduleQuestion = new ModuleQuestion();
        BeanUtils.copyProperties(updateModuleQuestionForm,moduleQuestion);
        int result = moduleQuestionMapper.updateQuestion(moduleQuestion);
        if(result != 0){
            return RespBean.SUCCESS("更新成功");
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }


    @Override
    public RespBean getAllQuestionsByOrder() {
        List<ModuleQuestionVo> questionVos = new ArrayList<>();
        List<ModuleQuestion> moduleQuestions = moduleQuestionMapper.selectAllQuestionsByOrder();
        for(ModuleQuestion moduleQuestion : moduleQuestions){
            ModuleQuestionVo moduleQuestionVo = new ModuleQuestionVo();
            BeanUtils.copyProperties(moduleQuestion,moduleQuestionVo);
            if(!questionVos.contains(moduleQuestionVo)) {
                questionVos.add(moduleQuestionVo);
            }
        }
        Integer initValue = moduleQuestions.size()*4/3+1;
        StringBuilder builder = new StringBuilder();
        Map<Integer, List<String>> map = new HashMap<>(initValue);
        for (ModuleQuestion mq : moduleQuestions){
            if(builder.indexOf(mq.getQuestionNo().toString()) > -1){
                map.get(mq.getQuestionNo()).add(mq.getDetailRequireId());
                continue;
            }
            else{
                List<String> list = new ArrayList<>();
                list.add(mq.getDetailRequireId());
                map.put(mq.getQuestionNo(),list);
                builder.append(mq.getQuestionNo().toString());
            }
        }
        for(ModuleQuestionVo vo : questionVos){
           vo.setDetailRequireIds(map.get(vo.getQuestionNo()));
        }
        return RespBean.SUCCESS(questionVos);
    }


    @Override
    public RespBean getSupportedDetailRequireIds(Integer moduleId) {
        Module module = moduleMapper.selectByPrimaryKey(moduleId);
        List<Relevancy> allRelevancy = relevancyMapper.getAllRelevancy();
        List<String> detailRequireIds = new ArrayList<>();
        for(Relevancy relevancy : allRelevancy){
            if(module.getCourseName().equals(relevancy.getCourseName())){
                detailRequireIds.add(relevancy.getDetailRequireId());
            }
        }
        if(!detailRequireIds.isEmpty()){
            return RespBean.SUCCESS(detailRequireIds);
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }

    /**
     * 中间处理方法
     * @param moduleId
     * @return
     */

    @Override
    public List<CalculateModuleSupportedScoreForm> CalculateTotalModuleSupportedScore(Integer moduleId) {
        return moduleQuestionMapper.selectModuleTotalScore(moduleId);
    }


    @Override
    public String getDetailIdByNo(Integer questionNo) {
        return moduleQuestionMapper.selectDetailIdByNo(questionNo);
    }
}
