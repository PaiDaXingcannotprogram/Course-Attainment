package com.go.courseattainment.service.impl;

import com.go.courseattainment.entities.DetailRequire;
import com.go.courseattainment.entities.GradeRequire;
import com.go.courseattainment.enums.RespBeanEnum;
import com.go.courseattainment.form.*;
import com.go.courseattainment.mapper.RequireMapper;
import com.go.courseattainment.service.RequireService;
import com.go.courseattainment.service.UserService;
import com.go.courseattainment.vo.DetailRequireSplitVo;
import com.go.courseattainment.vo.DetailRequireVo;
import com.go.courseattainment.vo.GradeRequireVo;
import com.go.courseattainment.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-03-29 21:04
 **/
@Service
@Slf4j
public class RequireServiceImpl implements RequireService {

    @Autowired
    private RequireMapper requireMapper;

    @Resource
    private UserService userService;


    @Override
    public RespBean getAllGradeRequires() {
        List<GradeRequireVo> gradeRequireVos = new ArrayList<>();
        Integer majorId = userService.getCurrentUser().getMajorId();

        List<GradeRequire> gradeRequires = requireMapper.selectAllGradeRequires(majorId);
        if(gradeRequires.isEmpty()){
            return RespBean.ERROR(RespBeanEnum.ERROR);
        }
        for(GradeRequire GR: gradeRequires){
            if(GR.getMajorId().equals(majorId)){
                GradeRequireVo requireVo = new GradeRequireVo();
                BeanUtils.copyProperties(GR,requireVo);
                gradeRequireVos.add(requireVo);
            }
        }
        return RespBean.SUCCESS(gradeRequireVos);
    }

    @Override
    public RespBean addGradeRequire(GradeRequireForm gradeRequireForm) {
        GradeRequire gradeRequire = new GradeRequire();
        BeanUtils.copyProperties(gradeRequireForm,gradeRequire);
        gradeRequire.setMajorId(userService.getCurrentUser().getMajorId());

        int result = requireMapper.insertIntoGradeRequire(gradeRequire);
        if(result != 0){
            return RespBean.SUCCESS("添加成功");
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }

    @Override
    public RespBean updateGradeRequire(UpdateGradeRequireForm updateGradeRequireForm) {
        Integer gradeRequireNo = updateGradeRequireForm.getGradeRequireNo();
        GradeRequire gradeRequire = requireMapper.selectOneByGradeRequireNo(gradeRequireNo);
        BeanUtils.copyProperties(updateGradeRequireForm,gradeRequire);

        int result = requireMapper.updateSetGradeRequire(gradeRequire);
        if(result != 0){
            return RespBean.SUCCESS("修改成功");
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }

    @Override
    public RespBean splitGradeRequire(Integer gradeRequireNo) {
        List<DetailRequireSplitVo> detailRequireSplitVos = new ArrayList<>();

        List<DetailRequire> detailRequires = requireMapper.selectByGradeRequireNo(gradeRequireNo);
        if(detailRequires.isEmpty()){
            return RespBean.ERROR(RespBeanEnum.ERROR);
        }

        for(DetailRequire DR : detailRequires){
            DetailRequireSplitVo detailRequireSplitVo = new DetailRequireSplitVo();
            BeanUtils.copyProperties(DR,detailRequireSplitVo);
            detailRequireSplitVos.add(detailRequireSplitVo);
        }
        Collections.sort(detailRequireSplitVos, new Comparator<DetailRequireSplitVo>() {
            @Override
            public int compare(DetailRequireSplitVo o1, DetailRequireSplitVo o2) {
                Integer n1 = Integer.valueOf(o1.getDetailRequireNo().substring(o1.getDetailRequireNo().length() - 1));
                Integer n2 = Integer.valueOf(o2.getDetailRequireNo().substring(o2.getDetailRequireNo().length() - 1));
                return n1 - n2;

            }
        });
        return RespBean.SUCCESS(detailRequireSplitVos);
    }


    @Override
    public RespBean getAllDetailRequires() {
        List<DetailRequireVo> detailRequireVos = new ArrayList<>();
        Integer majorId = userService.getCurrentUser().getMajorId();

        List<DetailRequire> detailRequires = requireMapper.selectAllDetailRequires(majorId);
        if(detailRequires.isEmpty()){
            return RespBean.ERROR(RespBeanEnum.ERROR);
        }
        for(DetailRequire DR : detailRequires){
            if(DR.getMajorId().equals(majorId)) {

                DetailRequireVo detailRequireVo = new DetailRequireVo();
                BeanUtils.copyProperties(DR,detailRequireVo);
                detailRequireVos.add(detailRequireVo);
            }
        }
        return RespBean.SUCCESS(detailRequireVos);
    }


    @Override
    public RespBean addDetailRequire(DetailRequireForm detailRequireForm) {
        DetailRequire detailRequire = new DetailRequire();

        BeanUtils.copyProperties(detailRequireForm,detailRequire);
        detailRequire.setMajorId(userService.getCurrentUser().getMajorId());

        log.info(userService.getCurrentUser().toString());

        int result = requireMapper.insertIntoDetailRequire(detailRequire);
        if(result != 0){
            return RespBean.SUCCESS("添加成功");
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }

    @Override
    public RespBean updateDetailRequire(UpdateDetailRequireForm updateDetailRequireForm) {
        Integer detailRequireId = updateDetailRequireForm.getDetailRequireId();

        DetailRequire detailRequire = requireMapper.selectOneByDetailRequireId(detailRequireId);
        BeanUtils.copyProperties(updateDetailRequireForm,detailRequire);

        int result = requireMapper.updateSetDetailRequire(detailRequire);
        if(result != 0){
            return RespBean.SUCCESS("修改成功");
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }


    @Override
    public RespBean deleteDetailRequire(Integer detailRequireId) {
        int result = requireMapper.deleteByPrimaryKey(detailRequireId);

        if(result != 0){
            return RespBean.SUCCESS("删除成功");
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }
}
