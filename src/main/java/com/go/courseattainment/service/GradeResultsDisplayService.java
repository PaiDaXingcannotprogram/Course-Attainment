package com.go.courseattainment.service;

import com.go.courseattainment.vo.RespBean;
import org.springframework.stereotype.Service;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-07-09 09:11
 **/
@Service
public interface GradeResultsDisplayService {


    /**
     * 获取当年年级的年级数据雷达图（最小值）
     * @param gradeId
     * @return RespBean
     */
    RespBean getShowingGradeRadarMapByMin(Integer gradeId);


    /**
     * 获取当年年级的年级数据雷达图（平均值）
     * @param gradeId
     * @return RespBean
     */
    RespBean getShowingGradeRadarMapByAverage(Integer gradeId);
}
