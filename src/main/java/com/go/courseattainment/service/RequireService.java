package com.go.courseattainment.service;

import com.go.courseattainment.entities.GradeRequire;
import com.go.courseattainment.form.DetailRequireForm;
import com.go.courseattainment.form.GradeRequireForm;
import com.go.courseattainment.form.UpdateDetailRequireForm;
import com.go.courseattainment.form.UpdateGradeRequireForm;
import com.go.courseattainment.vo.DetailRequireVo;
import com.go.courseattainment.vo.RespBean;
import org.springframework.stereotype.Service;

/**
 * @program: CourseAttainment
 * @description 要求require服务类
 * @author: 不会编程的派大星
 * @create: 2021-03-29 21:00
 **/
@Service
public interface RequireService {


    /**
     * 获取所有一级指标点
     * @return  RespBean
     */
    RespBean getAllGradeRequires();

    /**
     * 添加一级指标点
     * @param gradeRequireForm
     * @return  RespBean
     */
    RespBean addGradeRequire(GradeRequireForm gradeRequireForm);


    /**
     * 修改一级指标点
     * @param updateGradeRequireForm
     * @return  RespBean
     */
    RespBean updateGradeRequire(UpdateGradeRequireForm updateGradeRequireForm);


    /**
     * 根据一级指标点序号拆分一级指标点
     * @param gradeRequireNo
     * @return RespBean
     */
    RespBean splitGradeRequire(Integer gradeRequireNo);


    /**
     * 获取所有二级指标点
     * @return  RespBean
     */
    RespBean getAllDetailRequires();


    /**
     * 添加二级指标点
     * @return RespBean
     */
    RespBean addDetailRequire(DetailRequireForm detailRequireForm);


    /**
     * 修改二级指标点
     * @param updateDetailRequireForm
     * @return  RespBean
     */
    RespBean updateDetailRequire(UpdateDetailRequireForm updateDetailRequireForm);


    /**
     * 删除二级指标点
     * @param detailRequireId
     * @return  RespBean
     */
    RespBean deleteDetailRequire(Integer detailRequireId);


}
