package com.go.courseattainment.service;

import com.go.courseattainment.form.UploadScoreForm;
import com.go.courseattainment.vo.RespBean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-24 21:45
 **/
@Service
public interface UploadScoreService {


    /**
     * 根据学生学号查找学学生姓名
     * @param stuNo
     * @return RespBean
     */
    RespBean getStuNameByNo(String stuNo);


    /**
     * 上传学生模块小题成绩
     * @param stuScoreList
     * @return RespBean
     */
    RespBean uploadStuScore(List<UploadScoreForm> stuScoreList);


    /**
     * 更新 编辑uploadScore
     * @param uploadScoreForm
     * @return  RespBean
     */
    RespBean updateStuScore(UploadScoreForm uploadScoreForm);


    /**
     * 根据主键删除uploadScore
     * @param uploadScoreId
     * @return RespBean
     */
    RespBean deleteStuScore(Integer uploadScoreId);


    /**
     * 从缓存中取学生分数信息
     * @return RespBean
     */
    RespBean getAllScoresFromCache();
}
