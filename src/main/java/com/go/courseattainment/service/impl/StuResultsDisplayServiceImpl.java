package com.go.courseattainment.service.impl;


import com.go.courseattainment.entities.GradeRequire;
import com.go.courseattainment.entities.ResultsDisplay;
import com.go.courseattainment.entities.Student;
import com.go.courseattainment.enums.RespBeanEnum;
import com.go.courseattainment.mapper.RequireMapper;
import com.go.courseattainment.mapper.ResultsDisplayMapper;
import com.go.courseattainment.mapper.StudentMapper;
import com.go.courseattainment.service.StuResultsDisplayService;
import com.go.courseattainment.service.UserService;
import com.go.courseattainment.vo.RespBean;
import com.go.courseattainment.vo.ShowingStuAchievementVo;
import com.go.courseattainment.vo.ShowingStuInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-07-08 20:09
 **/
@Service
public class StuResultsDisplayServiceImpl implements StuResultsDisplayService {



    @Autowired
    private ResultsDisplayMapper resultsDisplayMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RequireMapper requireMapper;





    @Override
    public RespBean getShowingStuInfo(Integer gradeId) {
        Integer majorId = userService.getCurrentUser().getMajorId();
        List<ShowingStuInfo> showingStuList = new ArrayList<>();
        List<Student> students = studentMapper.selectCurrentMajorAndGradeStus(majorId, gradeId);
        for(Student stu : students){
            ShowingStuInfo showingStuInfo = new ShowingStuInfo();
            BeanUtils.copyProperties(stu,showingStuInfo);
            showingStuList.add(showingStuInfo);
        }
        if(!showingStuList.isEmpty()){
            return RespBean.SUCCESS(showingStuList);
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }

    @Override
    public RespBean getShowingStuInfoByNo(String stuNo) {
        Student student = studentMapper.selectByStuNo(stuNo);
        ShowingStuInfo showingStuInfo = new ShowingStuInfo();
        BeanUtils.copyProperties(student,showingStuInfo);
        return RespBean.SUCCESS(showingStuInfo);
    }

    @Override
    public RespBean getShowingStuInfoByName(String stuName) {
        List<ShowingStuInfo> showingStuList = new ArrayList<>();
        List<Student> students = studentMapper.selectByStuName(stuName);
        for(Student stu : students){
            ShowingStuInfo showingStuInfo = new ShowingStuInfo();
            BeanUtils.copyProperties(stu,showingStuInfo);
            showingStuList.add(showingStuInfo);
        }
        return RespBean.SUCCESS(showingStuList);
    }

    @Override
    public RespBean getShowingStuAchievement(Integer stuId) {
        List<ResultsDisplay> resultsDisplays = resultsDisplayMapper.selectAllDisplays();
        for(ResultsDisplay resultsDisplay : resultsDisplays){
            if(resultsDisplay.getStuId().equals(stuId)){
                ShowingStuAchievementVo stuAchievementVo = new ShowingStuAchievementVo();
                BeanUtils.copyProperties(resultsDisplay,stuAchievementVo);
                return RespBean.SUCCESS(stuAchievementVo);
            }
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }

    @Override
    public RespBean getShowingStuRadarMapByMin(Integer stuId) {
        List<ResultsDisplay> resultsDisplays = resultsDisplayMapper.selectAllDisplays();
        List<ResultsDisplay> stuRadarMapList = new ArrayList<>();
        for(ResultsDisplay resultsDisplay : resultsDisplays){
            if(resultsDisplay.getStuId().equals(stuId)){
                stuRadarMapList.add(resultsDisplay);
            }
        }
        List<GradeRequire> gradeRequires = requireMapper.selectAllGradeRequires(userService.getCurrentUser().getMajorId());
        HashMap<String, BigDecimal> stuRadarMap = new HashMap<>(gradeRequires.size());
        for(GradeRequire GR : gradeRequires){
            BigDecimal finalStuAchievement = getFinalMinStuAchievement(GR.getGradeRequireNo(),stuRadarMapList);
            stuRadarMap.put(GR.getBriefDesc(),finalStuAchievement);
        }
        if(!stuRadarMap.isEmpty()) {
            return RespBean.SUCCESS(stuRadarMap);
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }


    @Override
    public RespBean getShowingStuRadarMapByAverage(Integer stuId) {
        List<ResultsDisplay> resultsDisplays = resultsDisplayMapper.selectAllDisplays();
        List<ResultsDisplay> stuRadarMapList = new ArrayList<>();
        for(ResultsDisplay resultsDisplay : resultsDisplays){
            if(resultsDisplay.getStuId().equals(stuId)){
                stuRadarMapList.add(resultsDisplay);
            }
        }
        List<GradeRequire> gradeRequires = requireMapper.selectAllGradeRequires(userService.getCurrentUser().getMajorId());
        HashMap<String, BigDecimal> stuRadarMap = new HashMap<>(gradeRequires.size());
        for(GradeRequire GR : gradeRequires){
            BigDecimal finalStuAchievement = getFinalAverageStuAchievement(GR.getGradeRequireNo(),stuRadarMapList);
            stuRadarMap.put(GR.getBriefDesc(),finalStuAchievement);
        }
        if(!stuRadarMap.isEmpty()) {
            return RespBean.SUCCESS(stuRadarMap);
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }

    /**
     * 获取该学生一级指标点对应的最后的达成度
     * @param gradeRequireNo
     * @return
     */
    public BigDecimal getFinalMinStuAchievement(Integer gradeRequireNo,List<ResultsDisplay> resultsDisplays){
        StringBuilder temp = new StringBuilder();
        temp.append(gradeRequireNo.toString()).append(".");
        List<BigDecimal> list = new ArrayList<>();
        for(ResultsDisplay display : resultsDisplays){
            if(display.getDetailRequireId().contains(temp.toString())){
                list.add(display.getStuAchievement());
            }
        }
        return list.stream().min(BigDecimal::compareTo).get().setScale(2,BigDecimal.ROUND_HALF_UP);
    }





    public BigDecimal getFinalAverageStuAchievement(Integer gradeRequireNo,List<ResultsDisplay> resultsDisplays){
        StringBuilder cur = new StringBuilder();
        cur.append(gradeRequireNo.toString()).append(".");
        List<ResultsDisplay> list = new ArrayList<>();
        for(ResultsDisplay display : resultsDisplays){
            if(display.getDetailRequireId().contains(cur.toString())){
                list.add(display);
            }
        }
        return list.stream().map(ResultsDisplay::getStuAchievement).reduce(BigDecimal.ZERO,BigDecimal::add)
                .divide(BigDecimal.valueOf(list.size()),2,BigDecimal.ROUND_HALF_UP).setScale(2,BigDecimal.ROUND_HALF_UP);
    }


}
