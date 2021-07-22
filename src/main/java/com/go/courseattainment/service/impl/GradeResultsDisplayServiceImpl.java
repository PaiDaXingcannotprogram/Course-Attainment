package com.go.courseattainment.service.impl;

import com.go.courseattainment.entities.GradeRequire;
import com.go.courseattainment.entities.ResultsDisplay;
import com.go.courseattainment.entities.Student;
import com.go.courseattainment.enums.RespBeanEnum;
import com.go.courseattainment.mapper.RequireMapper;
import com.go.courseattainment.mapper.ResultsDisplayMapper;
import com.go.courseattainment.mapper.StudentMapper;
import com.go.courseattainment.service.GradeResultsDisplayService;
import com.go.courseattainment.service.UserService;
import com.go.courseattainment.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-07-08 20:28
 **/
@Service
@Slf4j
public class GradeResultsDisplayServiceImpl implements GradeResultsDisplayService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private UserService userService;

    @Autowired
    ResultsDisplayMapper resultsDisplayMapper;

    @Autowired
    private RequireMapper requireMapper;


    @Override
    public RespBean getShowingGradeRadarMapByMin(Integer gradeId) {
        List<Student> students = studentMapper.selectCurrentMajorAndGradeStus(userService.getCurrentUser().getMajorId(), gradeId);
        Map<String, BigDecimal> gradeRadarMap = new HashMap<>(students.size());
        List<ResultsDisplay> displays = resultsDisplayMapper.selectAllDisplays();
        List<ResultsDisplay> stuAchievements = new ArrayList<>();
        for(Student stu : students){
            for(ResultsDisplay display : displays){
                if(stu.getStuId().equals(display.getStuId())){
                    stuAchievements.add(display);
                }
            }
        }
        List<GradeRequire> gradeRequires = requireMapper.selectAllGradeRequires(userService.getCurrentUser().getMajorId());
        HashMap<String, BigDecimal> stuRadarMap = new HashMap<>(gradeRequires.size());
        for(GradeRequire GR : gradeRequires){
            BigDecimal finalStuAchievement = getFinalMinGradeAchievement(GR.getGradeRequireNo(),stuAchievements);
            stuRadarMap.put(GR.getBriefDesc(),finalStuAchievement);
        }
        if(!stuRadarMap.isEmpty()) {
            return RespBean.SUCCESS(stuRadarMap);
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }




    @Override
    public RespBean getShowingGradeRadarMapByAverage(Integer gradeId) {
        List<Student> students = studentMapper.selectCurrentMajorAndGradeStus(userService.getCurrentUser().getMajorId(), gradeId);
        Map<String, BigDecimal> gradeRadarMap = new HashMap<>(students.size());
        List<ResultsDisplay> displays = resultsDisplayMapper.selectAllDisplays();
        List<ResultsDisplay> stuAchievements = new ArrayList<>();
        for(Student stu : students){
            for(ResultsDisplay display : displays){
                if(stu.getStuId().equals(display.getStuId())){
                    stuAchievements.add(display);
                }
            }
        }
        List<GradeRequire> gradeRequires = requireMapper.selectAllGradeRequires(userService.getCurrentUser().getMajorId());
        HashMap<String, BigDecimal> stuRadarMap = new HashMap<>(gradeRequires.size());
        for(GradeRequire GR : gradeRequires){
            BigDecimal finalStuAchievement = getFinalAverageGradeAchievement(GR.getGradeRequireNo(),stuAchievements);
            stuRadarMap.put(GR.getBriefDesc(),finalStuAchievement);
        }
        if(!stuRadarMap.isEmpty()) {
            return RespBean.SUCCESS(stuRadarMap);
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }



    private BigDecimal getFinalMinGradeAchievement(Integer gradeRequireNo,List<ResultsDisplay> displays) {
        StringBuilder temp = new StringBuilder();
        temp.append(gradeRequireNo.toString()).append(".");
        List<BigDecimal> list = new ArrayList<>();
        for(ResultsDisplay display : displays){
            if(display.getDetailRequireId().contains(temp.toString())){
                list.add(display.getStuAchievement());
            }
        }
        return list.stream().min(BigDecimal::compareTo).get().setScale(2,BigDecimal.ROUND_HALF_UP);
    }



    private BigDecimal getFinalAverageGradeAchievement(Integer gradeRequireNo, List<ResultsDisplay> resultsDisplays) {
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
