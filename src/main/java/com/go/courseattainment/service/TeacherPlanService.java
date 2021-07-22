package com.go.courseattainment.service;

import com.go.courseattainment.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-07-05 19:51
 **/
@Service
public interface TeacherPlanService {


    /**
     * 获取当前登录老师的
     * @return
     */
    RespBean getCurrentPlans();


    /**
     * 该教学计划完成计算
     * @param planId
     * @return
     */
    RespBean finishCalculation(Integer planId);
}
