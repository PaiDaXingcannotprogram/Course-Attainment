package com.go.courseattainment.service;

import com.go.courseattainment.form.PlanForm;
import com.go.courseattainment.form.UpdatePlanForm;
import com.go.courseattainment.vo.RespBean;
import org.springframework.stereotype.Service;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-04-16 19:48
 **/
@Service
public interface PlanService {

    /**
     * 获取所有老师的用户名
     * @return  RespBean
     */
    RespBean getAllTeachers();


    /**
     * 添加教学计划
     * @param planForm
     * @return RespBean
     */
    RespBean addTeachPlan(PlanForm planForm);


    /**
     * 根据主键删除教学计划
     * @param planId
     * @return
     */
    RespBean deleteTeachPlan(Integer planId);


    /**
     * 编辑，更新教学计划
     * @param updatePlanForm
     * @return RespBean
     */
    RespBean updateTeachPlan(UpdatePlanForm updatePlanForm);


    /**
     * 获取所有教学计划
     * @return RespBean
     */
    RespBean getAllPlans();



}
