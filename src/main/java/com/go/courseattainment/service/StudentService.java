package com.go.courseattainment.service;

import com.go.courseattainment.ExcelForm.StudentExcelForm;
import com.go.courseattainment.form.UpdateStudentForm;
import com.go.courseattainment.vo.RespBean;
import com.go.courseattainment.vo.StudentPageVo;
import com.go.courseattainment.form.StudentForm;


import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @program: CourseAttainment
 * @description 学生模块方法类接口
 * @author: 不会编程的派大星
 * @create: 2021-04-15 10:53
 **/
@Service
public interface StudentService {


    /**
     * 添加单个学生
     * @param studentForm
     * @return  RespBean
     */
    RespBean addOneStu(StudentForm studentForm);

    /**
     *编辑学生信息
     * @param updateStudentForm
     * @return   RespBean
     */
    RespBean updateStudent(UpdateStudentForm updateStudentForm);






    /**
     * 删除单个学生
     * @param stuId
     * @return RespBean
     */
    RespBean deleteOne(Integer stuId);


    /**
     * 通过学号查找学生
     * @param stuNo
     * @return RespBean
     */
    RespBean getStuByNo(String stuNo);


    /**
     * 通过学生姓名查找学生
     * @param stuName
     * @return RespBean
     */
    RespBean getStuByName(String stuName,Integer gradeId);




    /**
     * 获取该年级 该专业所有学生信息
     * @param gradeId
     * @return RespBean
     */
    RespBean getCurrentMajorAndGradeStus(Integer gradeId);




    /**
     * 查找学生，分页传给前端
     * @param studentPageVo
     * @return  RespBean
     */
    RespBean findStudentByPage(StudentPageVo studentPageVo);


    /**
     * 批量添加学生信息
     * @param studentExcelFormList
     * @return RespBean
     */
    RespBean addBatchStus(List<StudentExcelForm> studentExcelFormList);


    /**
     * 下载模板
     * @param response
     * @return  RespBean
     */
    //RespBean uploadTemplate( HttpServletRequest request,HttpServletResponse response);

    /**
     * 生成Excel模板
     * @return
     */
    //XSSFWorkbook show();




}
