package com.go.courseattainment.service.impl;

import com.go.courseattainment.entities.*;
import com.go.courseattainment.enums.RespBeanEnum;
import com.go.courseattainment.mapper.*;
import com.go.courseattainment.service.RelevancyService;
import com.go.courseattainment.service.TeacherPlanService;
import com.go.courseattainment.service.UserService;
import com.go.courseattainment.vo.RespBean;
import com.go.courseattainment.vo.TPlanVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-07-05 19:52
 **/
@Service
@Slf4j
public class TeacherPlanServiceImpl implements TeacherPlanService {

    @Autowired
    private PlanMapper planMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UploadScoreMapper uploadScoreMapper;

    @Autowired
    private ModuleMapper moduleMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private RelevancyService relevancyService;

    @Autowired
    private ResultsDisplayMapper resultsDisplayMapper;



    @Override
    public RespBean getCurrentPlans() {
        User user = userService.getCurrentUser();
        String teacherName = user.getName();
        List<TPlanVo> TPlanVoList = new ArrayList<>();
        List<Plan> plans = planMapper.selectAllPlans();
        for (Plan plan : plans){
           if(plan.getCourseTeacher().equals(teacherName)){
               TPlanVo vo = new TPlanVo();
               BeanUtils.copyProperties(plan,vo);
               TPlanVoList.add(vo);
           }
        }
        if(!TPlanVoList.isEmpty()){
            return RespBean.SUCCESS(TPlanVoList);
        }
        return RespBean.ERROR(RespBeanEnum.CURRENT_TEACHER_PLAN_EMPTY);
    }


    @Override
    public RespBean finishCalculation(Integer planId) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        boolean flag = updateTPlan(planId, formatter.format(date.getTime()));
        if(!flag){
            return RespBean.ERROR(RespBeanEnum.ERROR);
        }
        Boolean isFlag = CalculatePlanTotalScores(planId);
        if(!isFlag){
            return RespBean.ERROR(RespBeanEnum.ERROR);
        }
        return RespBean.SUCCESS("该教学计划已计算并提交");
    }





    public boolean updateTPlan(Integer planId, String computeTime){
        Plan plan = planMapper.selectByPrimaryKey(planId);
        plan.setCourseStatus(1);
        plan.setComputeTime(computeTime);
        int result = planMapper.updatePlan(plan);
        if(result != 0){
            return true;
        }
        return false;
    }




    /**
     * 计算每个学生对应的每一个二级指标点下的总课程达标度
     * @param planId
     * @return
     */
    public Boolean CalculatePlanTotalScores(Integer planId){
        Boolean flag = true;
        List<Module> modules = moduleMapper.selectAllModules();
        for(Module module : modules){
            String courseName = module.getCourseName();
            if(module.getPlanId().equals(planId)){
                List<UploadScore> scores = uploadScoreMapper.selectByModuleId(module.getModuleId());
                Set<String > stuNoSet = getStuNoSet(scores);
                Boolean stuPlanScore = getStuPlanScore(stuNoSet, scores, module);
                if(stuPlanScore.equals(false)){
                    flag = false;
                }
            }
        }
        return flag;
    }


    /**
     * 获取不重复的学生学号
     * @param list
     * @return set
     */
    public Set<String> getStuNoSet(List<UploadScore> list) {
        Set<String> stuNoSet = new HashSet<>();
        for (UploadScore uploadScore : list) {
            stuNoSet.add(uploadScore.getStuNo());
        }
        return stuNoSet;
    }


    /**
     * 计算每个学生该课程对应不同二级指标点的
     * @param stuNoSet
     * @param scoreList
     * @param module
     * @return
     */
    public Boolean getStuPlanScore(Set<String> stuNoSet,List<UploadScore> scoreList,Module module){
        Boolean flag = false;
        for(String stuNo : stuNoSet){
            BigDecimal stuPlanScore = new BigDecimal(0.0);
            Student student = studentMapper.selectByStuNo(stuNo);
            Set<String> idSets = new HashSet<>();
            for(UploadScore uploadScore : scoreList){
                if(uploadScore.getStuNo().equals(stuNo)){
                    stuPlanScore.add(uploadScore.getModuleScore());
                    idSets.add(uploadScore.getDetailRequireId());
                }
            }
            for(String requireId: idSets){
                ResultsDisplay display = new ResultsDisplay();
                BeanUtils.copyProperties(student,display);
                display.setCourseName(module.getCourseName());
                display.setDetailRequireId(requireId);
                BigDecimal correlation = relevancyService.getCorrelationByIdAndName(requireId, module.getCourseName());
                display.setCorrelation(correlation);
                if(!correlation.equals(new BigDecimal(0.0))){
                    display.setStuAchievement(stuPlanScore.multiply(correlation));
                    int result = resultsDisplayMapper.insertIntoDisplay(display);
                    if(result != 0){
                        flag = true;
                    }
                }
            }

        }
        return flag;
    }


}
