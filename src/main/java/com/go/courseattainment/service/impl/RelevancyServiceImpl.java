package com.go.courseattainment.service.impl;

import com.go.courseattainment.entities.CourseScheme;
import com.go.courseattainment.entities.DetailRequire;
import com.go.courseattainment.entities.Relevancy;
import com.go.courseattainment.enums.RespBeanEnum;
import com.go.courseattainment.form.RelevancyForm;
import com.go.courseattainment.mapper.CourseSchemeMapper;
import com.go.courseattainment.mapper.RelevancyMapper;
import com.go.courseattainment.mapper.RequireMapper;
import com.go.courseattainment.service.RelevancyService;
import com.go.courseattainment.service.UserService;
import com.go.courseattainment.vo.CourseSchemeVo;
import com.go.courseattainment.vo.RelevancyVo;
import com.go.courseattainment.vo.RespBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-04-07 21:08
 **/
@Service
public class RelevancyServiceImpl implements RelevancyService {

    @Autowired
    private RelevancyMapper relevancyMapper;

    @Autowired
    private CourseSchemeMapper courseSchemeMapper;

    @Autowired
    private RequireMapper requireMapper;

    @Autowired
    private UserService userService;


    @Override
    public RespBean addRelevancy(RelevancyVo relevancyVo) {
        Relevancy relevancy = new Relevancy();
        relevancy.setMajorId(userService.getCurrentUser().getMajorId());
        BeanUtils.copyProperties(relevancyVo,relevancy);

        int result = relevancyMapper.insertRelevancy(relevancy);
        if(result == 1){
            return RespBean.SUCCESS("添加成功");
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }




    @Override
    public RespBean getAllCourseInfos() {
        List<CourseSchemeVo> courseSchemeVos = new ArrayList<>();
        List<CourseScheme> courseSchemes = courseSchemeMapper.selectByMajorId(userService.getCurrentUser().getMajorId());

        if(!courseSchemes.isEmpty()) {
            for (CourseScheme cs : courseSchemes) {
                if (!cs.getCourseName().isEmpty()) {
                    CourseSchemeVo vo = new CourseSchemeVo();
                    BeanUtils.copyProperties(cs, vo);
                    courseSchemeVos.add(vo);
                }
            }
            return RespBean.SUCCESS(courseSchemeVos);
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }


    @Override
    public RespBean getAllDetailNos() {
        List<String> detailRequireNos = new ArrayList<>();
        List<DetailRequire> detailRequires = requireMapper.selectAllDetailRequires(userService.getCurrentUser().getMajorId());

        if(!detailRequires.isEmpty()) {
            for (DetailRequire dr : detailRequires) {
                if(dr.getMajorId().equals(userService.getCurrentUser().getMajorId())){
                    detailRequireNos.add(dr.getDetailRequireNo());
                }
            }
            return RespBean.SUCCESS(detailRequireNos);
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }


    @Override
    public RespBean addOneCourseInfo(CourseSchemeVo courseSchemeVo) {

        CourseScheme courseScheme = new CourseScheme();
        courseScheme.setMajorId(userService.getCurrentUser().getMajorId());
        BeanUtils.copyProperties(courseSchemeVo,courseScheme);
        int result = courseSchemeMapper.insertIntoCourseScheme(courseScheme);
        if(result != 0){
            return RespBean.SUCCESS("添加成功");
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }


    @Override
    public RespBean setCourseRelevancy(List<RelevancyForm> courseRelevancyList) {
        Integer majorId = userService.getCurrentUser().getMajorId();
        List<String> relevancyErrorList = new ArrayList<>();
        for(RelevancyForm rf : courseRelevancyList){
            Relevancy relevancy = new Relevancy();
            relevancy.setMajorId(majorId);
            relevancy.setCourseName(rf.getCourseName());
            IdentityHashMap<String, BigDecimal> relevancyMap = rf.getRelevancyMap();
            Iterator<String> iterator = relevancyMap.keySet().iterator();
            while(iterator.hasNext()){
                String key = iterator.next();
                relevancy.setDetailRequireId(key);
                relevancy.setCorrelation(relevancyMap.get(key));
            }
            int result = relevancyMapper.insertRelevancy(relevancy);
            if(result == 0){
                relevancyErrorList.add(relevancy.getCourseName());
            }
        }
        if(relevancyErrorList.isEmpty()){
            return RespBean.SUCCESS("添加成功");
        }
        return RespBean.ERROR(RespBeanEnum.RELEVANCY_ADD_ERROR,relevancyErrorList);
    }


    @Override
    public BigDecimal getCorrelationByIdAndName(String detailRequireId, String courseName) {
        List<Relevancy> allRelevancy = relevancyMapper.getAllRelevancy();
        for(Relevancy relevancy : allRelevancy){
            if(relevancy.getCourseName().equals(courseName) && relevancy.getDetailRequireId().equals(detailRequireId)){
                return relevancy.getCorrelation();
            }
        }
        return new BigDecimal(0.0);
    }
}
