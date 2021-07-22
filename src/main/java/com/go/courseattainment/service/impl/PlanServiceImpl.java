package com.go.courseattainment.service.impl;

import com.go.courseattainment.entities.Plan;
import com.go.courseattainment.enums.RespBeanEnum;
import com.go.courseattainment.form.PlanForm;
import com.go.courseattainment.form.UpdatePlanForm;
import com.go.courseattainment.mapper.PlanMapper;
import com.go.courseattainment.mapper.UserMapper;
import com.go.courseattainment.service.PlanService;
import com.go.courseattainment.service.UserService;
import com.go.courseattainment.vo.PlanVo;
import com.go.courseattainment.vo.RespBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: CourseAttainment
 * @description 课程计划接口方法实现类
 * @author: 不会编程的派大星
 * @create: 2021-04-16 19:48
 **/
@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private PlanMapper planMapper;

    @Override
    public RespBean getAllTeachers() {
        List<String> teacherNames = userMapper.selectAllTeacherNames();
        return RespBean.SUCCESS(teacherNames);
    }


    @Override
    public RespBean addTeachPlan(PlanForm planForm) {
        Plan plan = new Plan();
        plan.setMajorId(userService.getCurrentUser().getMajorId());
        plan.setCourseStatus(0);
        BeanUtils.copyProperties(planForm,plan);
        int result = planMapper.insertIntoPlaN(plan);
        if(result != 0){
            return  RespBean.SUCCESS("添加成功");
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }


    @Override
    public RespBean deleteTeachPlan(Integer planId) {
        int result = planMapper.deleteByPlanId(planId);
        if(result != 0){
            return RespBean.SUCCESS("删除成功");
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }

    @Override
    public RespBean updateTeachPlan(UpdatePlanForm updatePlanForm) {
        Plan plan = planMapper.selectByPrimaryKey(updatePlanForm.getPlanId());
        BeanUtils.copyProperties(updatePlanForm,plan);
        int result = planMapper.insertIntoPlaN(plan);
        if(result != 0){
            return RespBean.SUCCESS("编辑成功");
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }


    @Override
    public RespBean getAllPlans() {
        List<PlanVo> planVoList = new ArrayList<>();
        List<Plan> plans = planMapper.selectAllPlans();
        if(!plans.isEmpty()){
            for (Plan plan: plans){
                PlanVo planVo = new PlanVo();
                BeanUtils.copyProperties(plan,planVo);
                planVoList.add(planVo);
            }
            return RespBean.SUCCESS(planVoList);
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }

}
