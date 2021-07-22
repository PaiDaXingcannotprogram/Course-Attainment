package com.go.courseattainment.service;

import com.go.courseattainment.entities.CourseTarget;
import com.go.courseattainment.form.CourseTargetForm;
import com.go.courseattainment.form.UpdateCourseTargetForm;
import com.go.courseattainment.vo.RespBean;
import org.springframework.stereotype.Service;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-02 15:29
 **/
@Service
public interface CourseTargetService {


    /**
     * 添加课程目标
     * @param courseTargetForm
     * @return RespBean
     */
    RespBean addCourseTarget(CourseTargetForm courseTargetForm);


    /**
     * 编辑课程目标
     * @param updateCourseTargetForm
     * @return  RespBean
     */
    RespBean updateCourseTarget(UpdateCourseTargetForm updateCourseTargetForm);


    /**
     * 删除课程目标
     * @param courseTargetId
     * @return RespBean
     */
    RespBean deleteCourseTarget(Integer courseTargetId);


    /**
     *获取所有排序好的课程目标
     * @return RespBean
     */
    RespBean getAllTargetsByOrder();

}
