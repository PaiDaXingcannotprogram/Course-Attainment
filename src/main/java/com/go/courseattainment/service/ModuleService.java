package com.go.courseattainment.service;

import com.go.courseattainment.form.ModuleForm;
import com.go.courseattainment.form.ModuleQuestionForm;
import com.go.courseattainment.form.UpdateModuleForm;
import com.go.courseattainment.form.UpdateModuleQuestionForm;
import com.go.courseattainment.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-20 18:28
 **/
@Service
public interface ModuleService {

    /**
     * 根据主键获取module
     * @param moduleId
     * @return RespBean
     *
     */
    RespBean getModuleByPrimaryKey(Integer moduleId);


    /**
     * 添加新的模块
     * @param moduleForm
     * @return RespBean
     */
    RespBean addModule(ModuleForm moduleForm);


    /**
     * 根据主键删除module
     * @param moduleId
     * @return RespBean
     */
    RespBean deleteModule(Integer moduleId);


    /**
     * 更新编辑module
     * @param updateModuleForm
     * @return RespBean
     */
    RespBean updateModule(UpdateModuleForm updateModuleForm);


    /**
     * 获取所有Module
     * @return RespBean
     */
    RespBean getAllModules();


    /**
     * 获取所有该课程下的所有模块
     * @param planId
     * @return  RespBean
     */
    RespBean getAllCourseModules(Integer planId);




}
