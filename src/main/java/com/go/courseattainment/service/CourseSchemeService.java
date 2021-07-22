package com.go.courseattainment.service;

import com.go.courseattainment.vo.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-04-02 17:57
 **/
@Service
public interface CourseSchemeService {
    /**
     * 软工选课方案 sheet1
     * @param courseList
     * @param majorId
     * @return
     */
    RespBean saveSeCourseSchemesS1(List<SeCourseSchemeExcelS1Vo> courseList, Integer majorId);

    /**
     * 软工选课方案 sheet2
     * @param courseList
     * @param majorId
     * @return
     */
    RespBean saveSeCourseSchemesS2(List<SeCourseSchemeExcelS2Vo> courseList, Integer majorId);

    /**
     * 计科选课方案 sheet1
     * * @param courseList
     * @param majorId
     * @return RespBean
     */
    RespBean saveJkCourseSchemesS1(List<JkCourseSchemeExcelS1Vo> courseList, Integer majorId);

    /**
     * 计科选课方案 sheet2
     * * @param courseList
     * @param majorId
     * @return RespBean
     */
    RespBean saveJkCourseSchemesS2(List<JkCourseSchemeExcelS2Vo> courseList, Integer majorId);


    /**
     * 根据课程号查找选课方案
     * @param courseId
     * @return RespBean
     */
    RespBean getSchemeByCourseId(String courseId);





}
