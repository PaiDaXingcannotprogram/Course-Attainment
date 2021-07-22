package com.go.courseattainment.service;

import com.go.courseattainment.vo.RespBean;
import org.springframework.stereotype.Service;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-07-08 20:09
 **/
@Service
public interface StuResultsDisplayService {

    /**
     * 获取当前需要展的学生信息
     * @param gradeId
     * @return RespBean
     */
    RespBean getShowingStuInfo(Integer gradeId);


    /**
     * 根据学号获取需要展示的学生信息
     * @param stuNo
     * @return RespBean
     */
    RespBean getShowingStuInfoByNo(String stuNo);


    /**
     * 根据学生姓名获取需要在展示的学生信息
     * @param stuName
     * @return RespBean
     */
    RespBean getShowingStuInfoByName(String stuName);


    /**
     * 获取要展示的学生的达成度情况
     * @param stuId
     * @return RespBean
     */
    RespBean getShowingStuAchievement(Integer stuId);


    /**
     * 获取当前要展示的学生的雷达图数据(最小值)
     * @param stuId
     * @return RespBean
     */
    RespBean getShowingStuRadarMapByMin(Integer stuId);


    /**
     * 获取当前要展示的学生的雷达图数据(平均值)
     * @param stuId
     * @return RespBean
     */
    RespBean getShowingStuRadarMapByAverage(Integer stuId);













}
