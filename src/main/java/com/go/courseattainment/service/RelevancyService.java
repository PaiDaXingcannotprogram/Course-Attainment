package com.go.courseattainment.service;

import com.go.courseattainment.form.RelevancyForm;
import com.go.courseattainment.vo.CourseSchemeVo;
import com.go.courseattainment.vo.RelevancyVo;
import com.go.courseattainment.vo.RespBean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-04-07 21:08
 **/
@Service
public interface RelevancyService {

    /**
     * 添加课程和关联度
     * @param relevancyVo
     * @return  RespBean
     */
    RespBean addRelevancy(RelevancyVo relevancyVo);


    /**
     * 获取所有课程信息
     * @return RespBean
     */
    RespBean getAllCourseInfos();


    /**
     * 获取所有二级指标点Id
     * @return RespBean
     */
    RespBean getAllDetailNos();


    /**
     *添加单条课程信息
     * @param courseSchemeVo
     * @return RespBean
     */
    RespBean addOneCourseInfo(CourseSchemeVo courseSchemeVo);


    /**
     * 设置课程与指标之间的关联度
     * @param courseRelevancyList
     * @return RespBean
     */
    RespBean setCourseRelevancy(List<RelevancyForm> courseRelevancyList);


    /**
     * 根据requreId和courseName获取关联度
     * @param detailRequireId
     * @param courseName
     * @return  BigDecimal
     */
    BigDecimal getCorrelationByIdAndName(String detailRequireId, String courseName);

}
