package com.go.courseattainment.service.impl;

import com.go.courseattainment.entities.CourseTarget;
import com.go.courseattainment.enums.RespBeanEnum;
import com.go.courseattainment.form.CourseTargetForm;
import com.go.courseattainment.form.UpdateCourseTargetForm;
import com.go.courseattainment.mapper.CourseSchemeMapper;
import com.go.courseattainment.mapper.CourseTargetMapper;
import com.go.courseattainment.service.CourseTargetService;
import com.go.courseattainment.vo.CourseTargetVo;
import com.go.courseattainment.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-02 15:30
 **/
@Service
@Slf4j
public class CourseTargetServiceImpl implements CourseTargetService {

    @Autowired
    private CourseTargetMapper courseTargetMapper;


    @Override
    public RespBean addCourseTarget(CourseTargetForm courseTargetForm) {
        CourseTarget courseTarget = new CourseTarget();
        BeanUtils.copyProperties(courseTargetForm,courseTarget);
        int result = courseTargetMapper.insertIntoCourseTarget(courseTarget);
        if(result != 0){
            return RespBean.SUCCESS("添加成功");
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }


    @Override
    public RespBean updateCourseTarget(UpdateCourseTargetForm updateCourseTargetForm) {
        CourseTarget courseTarget = new CourseTarget();
        BeanUtils.copyProperties(updateCourseTargetForm,courseTarget);
        int result = courseTargetMapper.updateSetCourseTarget(courseTarget);
        if(result != 0){
            return RespBean.SUCCESS("编辑成功");
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }

    @Override
    public RespBean deleteCourseTarget(Integer courseTargetId) {
        int result = courseTargetMapper.deleteFromCourseTarget(courseTargetId);
        if(result != 0){
            return RespBean.SUCCESS("删除成功");
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }


    @Override
    public RespBean getAllTargetsByOrder() {
        List<CourseTargetVo> targetVoList = new ArrayList<>();

        List<CourseTarget> courseTargets = courseTargetMapper.selectAllCourseTargetsByOrder();
        if(!courseTargets.isEmpty()){
            for(CourseTarget Ct : courseTargets){
                CourseTargetVo targetVo = new CourseTargetVo();
                BeanUtils.copyProperties(Ct,targetVo);
                targetVoList.add(targetVo);
            }
            return RespBean.SUCCESS(targetVoList);
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }
}
