package com.go.courseattainment.service.impl;

import com.go.courseattainment.entities.CourseScheme;
import com.go.courseattainment.entities.DetailRequire;
import com.go.courseattainment.enums.RespBeanEnum;
import com.go.courseattainment.mapper.CourseSchemeMapper;
import com.go.courseattainment.mapper.RequireMapper;
import com.go.courseattainment.service.CourseSchemeService;
import com.go.courseattainment.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-04-02 18:38
 **/
@Service
public class CourseSchemeServiceImpl implements CourseSchemeService {


    @Autowired
    private CourseSchemeMapper courseSchemeMapper;

    @Autowired
    private RequireMapper requireMapper;

    @Override
    public RespBean saveSeCourseSchemesS1(List<SeCourseSchemeExcelS1Vo> courseList, Integer majorId) {
        if(courseList == null){
            return RespBean.ERROR(RespBeanEnum.EXCEL_BLANK);
        }
        int result = 0;
        List<CourseScheme> repeatList = new ArrayList<>();
        List<CourseScheme> list = new ArrayList<>();
        for(SeCourseSchemeExcelS1Vo vo : courseList){
            CourseScheme courseScheme = new CourseScheme();
            BeanUtils.copyProperties(vo,courseScheme);
            if(majorId == 0){
                courseScheme.setMajorId(0);
            }else {
                courseScheme.setMajorId(1);
            }
            list.add(courseScheme);
        }

        for(CourseScheme cs :list){
            CourseScheme scheme = courseSchemeMapper.selectBycourseId(cs.getCourseId());
            if(scheme != null){
                repeatList.add(cs);
                continue;
            }
            result = courseSchemeMapper.insertIntoCourseScheme(cs);
            if(result != 1){
                return RespBean.ERROR(RespBeanEnum.SCHEME_REPEAT,repeatList);
            }
        }
        return RespBean.SUCCESS("添加成功");
    }

    @Override
    public RespBean saveSeCourseSchemesS2(List<SeCourseSchemeExcelS2Vo> courseList, Integer majorId) {
        if(courseList == null){
            return RespBean.ERROR(RespBeanEnum.EXCEL_BLANK);
        }
        int result = 0;
        List<CourseScheme> repeatList = new ArrayList<>();
        List<CourseScheme> list = new ArrayList<>();
        for(SeCourseSchemeExcelS2Vo vo : courseList){
            CourseScheme courseScheme = new CourseScheme();
            BeanUtils.copyProperties(vo,courseScheme);
            if(majorId == 0){
                courseScheme.setMajorId(0);
            }else {
                courseScheme.setMajorId(1);
            }
            list.add(courseScheme);
        }

        for(CourseScheme cs :list){
            CourseScheme scheme = courseSchemeMapper.selectBycourseId(cs.getCourseId());
            if(scheme != null){
                repeatList.add(cs);
                continue;
            }
            result = courseSchemeMapper.insertIntoCourseScheme(cs);
            if(result != 1){
                return RespBean.ERROR(RespBeanEnum.SCHEME_REPEAT,repeatList);
            }
        }
        return RespBean.SUCCESS("添加成功");
    }

    @Override
    public RespBean saveJkCourseSchemesS1(List<JkCourseSchemeExcelS1Vo> courseList, Integer majorId) {
        if(courseList == null){
            return RespBean.ERROR(RespBeanEnum.EXCEL_BLANK);
        }
        int result = 0;
        List<CourseScheme> repeatList = new ArrayList<>();
        List<CourseScheme> list = new ArrayList<>();
        for(JkCourseSchemeExcelS1Vo vo : courseList){
            CourseScheme courseScheme = new CourseScheme();
            BeanUtils.copyProperties(vo,courseScheme);
            if(majorId == 0){
                courseScheme.setMajorId(0);
            }else {
                courseScheme.setMajorId(1);
            }
            list.add(courseScheme);
        }

        for(CourseScheme cs :list){
            CourseScheme scheme = courseSchemeMapper.selectBycourseId(cs.getCourseId());
            if(scheme != null){
                repeatList.add(cs);
                continue;
            }
            result = courseSchemeMapper.insertIntoCourseScheme(cs);
            if(result != 1){
                return RespBean.ERROR(RespBeanEnum.SCHEME_REPEAT,repeatList);
            }
        }
        return RespBean.SUCCESS("添加成功");
    }


    @Override
    public RespBean saveJkCourseSchemesS2(List<JkCourseSchemeExcelS2Vo> courseList, Integer majorId) {
        if(courseList == null){
            return RespBean.ERROR(RespBeanEnum.EXCEL_BLANK);
        }
        int result = 0;
        List<CourseScheme> repeatList = new ArrayList<>();
        List<CourseScheme> list = new ArrayList<>();
        for(JkCourseSchemeExcelS2Vo vo : courseList){
            CourseScheme courseScheme = new CourseScheme();
            BeanUtils.copyProperties(vo,courseScheme);
            if(majorId == 0){
                courseScheme.setMajorId(0);
            }else {
                courseScheme.setMajorId(1);
            }
            list.add(courseScheme);
        }

        for(CourseScheme cs :list){
            CourseScheme scheme = courseSchemeMapper.selectBycourseId(cs.getCourseId());
            if(scheme != null){
                repeatList.add(cs);
                continue;
            }
            result = courseSchemeMapper.insertIntoCourseScheme(cs);
            if(result != 1){
                return RespBean.ERROR(RespBeanEnum.SCHEME_REPEAT,repeatList);
            }
        }
        return RespBean.SUCCESS("添加成功");
    }




    @Override
    public RespBean getSchemeByCourseId(String courseId) {
        CourseScheme courseScheme = courseSchemeMapper.selectBycourseId(courseId);
        if(courseScheme != null){
            return RespBean.SUCCESS(courseScheme);
        }
        return RespBean.ERROR(RespBeanEnum.SCHEME_NOT_EXIST);
    }



}
