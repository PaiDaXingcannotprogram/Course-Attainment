package com.go.courseattainment.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.go.courseattainment.ExcelForm.StudentExcelForm;
import com.go.courseattainment.entities.Student;
import com.go.courseattainment.enums.RespBeanEnum;
import com.go.courseattainment.form.UpdateStudentForm;
import com.go.courseattainment.mapper.StudentMapper;
import com.go.courseattainment.service.StudentService;
import com.go.courseattainment.service.UserService;
import com.go.courseattainment.vo.RespBean;
import com.go.courseattainment.vo.StudentPageVo;
import com.go.courseattainment.form.StudentForm;
import com.go.courseattainment.vo.StudentVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.Line;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: CourseAttainment
 * @description 学生模块方法接口实现类
 * @author: 不会编程的派大星
 * @create: 2021-04-15 10:54
 **/
@Service
@Slf4j
public class StudentServiceImpl implements StudentService{


    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private UserService userService;

    @Override
    public RespBean addOneStu(StudentForm studentForm) {
        Student student = new Student();
        Student stu = studentMapper.selectByStuNo(studentForm.getStuNo());
        if (stu != null) {
            return RespBean.ERROR(RespBeanEnum.STU_IS_EXIST);
        }
        Integer majorId = userService.getCurrentUser().getMajorId();
        student.setMajorId(majorId);
        BeanUtils.copyProperties(studentForm, student);

        int result = studentMapper.insertIntoStu(student);
        if (result != 0) {
            return RespBean.SUCCESS("添加成功");
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }


    @Override
    public RespBean updateStudent(UpdateStudentForm updateStudentForm) {
        Integer stuId = updateStudentForm.getStuId();

        Student student = studentMapper.selectByPrimaryKey(stuId);
        BeanUtils.copyProperties(updateStudentForm, student);

        int result = studentMapper.updateStu(student);
        if (result != 0) {
            return RespBean.SUCCESS("编辑成功");
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }


    @Override
    public RespBean deleteOne(Integer stuId) {
        int result = studentMapper.deleteStuByStuId(stuId);
        if (result != 0) {
            return RespBean.SUCCESS("删除成功");
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }


    @Override
    public RespBean getStuByNo(String stuNo) {
        StudentVo studentVo = new StudentVo();
        Student student = studentMapper.selectByStuNo(stuNo);
        if (student != null) {
            BeanUtils.copyProperties(student, studentVo);
            return RespBean.SUCCESS(studentVo);
        }
        return RespBean.ERROR(RespBeanEnum.STU_NOT_EXIST);
    }

    @Override
    public RespBean getStuByName(String stuName,Integer gradeId) {
        List<StudentVo> studentVos = new ArrayList<>();

        List<Student> students = studentMapper.selectByStuName(stuName);
        if (students != null) {
            for (Student st : students) {
                if(st.getGradeId().equals(gradeId)){
                    StudentVo vo = new StudentVo();
                    BeanUtils.copyProperties(st, vo);
                    studentVos.add(vo);
                }
            }
            return RespBean.SUCCESS(studentVos);
        }
        return RespBean.ERROR(RespBeanEnum.STU_NOT_EXIST);
    }


    @Override
    public RespBean getCurrentMajorAndGradeStus(Integer gradeId) {
        List<StudentVo> studentVos = new ArrayList<>();
        Integer majorId = userService.getCurrentUser().getMajorId();
        List<Student> students = studentMapper.selectCurrentMajorAndGradeStus(majorId, gradeId);
        if (students != null) {
            for (Student st : students) {
                StudentVo vo = new StudentVo();
                BeanUtils.copyProperties(st, vo);
                studentVos.add(vo);
            }
            return RespBean.SUCCESS(studentVos);
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }


    @Override
    public RespBean findStudentByPage(StudentPageVo studentPageVo) {
        Integer majorId = userService.getCurrentUser().getMajorId();
        PageHelper.startPage(studentPageVo.getPageNum(), studentPageVo.getPageSize());
        List<Student> studentList = studentMapper.selectCurrentMajorAndGradeStus(majorId, studentPageVo.getGradeId());
        List<StudentVo> studentVos = new ArrayList<>();
        log.info(studentList.toString());
        if (studentList != null) {
            for (Student st : studentList) {
                StudentVo vo = new StudentVo();
                BeanUtils.copyProperties(st, vo);
                studentVos.add(vo);
            }
            PageInfo<StudentVo> pageInfo = new PageInfo<>(studentVos);
            return RespBean.SUCCESS(pageInfo);
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }


    @Override
    public RespBean addBatchStus(List<StudentExcelForm> studentExcelFormList) {
        List<StudentExcelForm> stuAddErrorList = new ArrayList<>();
        for (StudentExcelForm form : studentExcelFormList) {
            Student student = new Student();
            BeanUtils.copyProperties(form, student);
            log.info(student.toString());
            int result = studentMapper.insertIntoStu(student);
            if (result == 0) {
                stuAddErrorList.add(form);
                continue;
            }
        }
        if (!stuAddErrorList.isEmpty()) {
            return RespBean.ERROR(RespBeanEnum.SOME_STU_ADD_ERROR, stuAddErrorList);
        }
        return RespBean.SUCCESS();
    }


//    @Override
//    public RespBean uploadTemplate(HttpServletRequest request,HttpServletResponse response) {
//
//        XSSFWorkbook wb = this.show();
//        String fileName = "学生信息模板";
//        ServletOutputStream outputStream = null;
//        try {
//            fileName = URLEncoder.encode(fileName, "UTF-8");
//            //设置ContentType请求信息格式
//            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
//            response.setHeader("Content-disposition", "attachment;filename=" + fileName+".xlsx");
//            outputStream = response.getOutputStream();
//            wb.write(outputStream);
//            outputStream.flush();
//            outputStream.close();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return RespBean.SUCCESS("下载成功");
//    }
//
//    @Override
//    public XSSFWorkbook show() {
//
//        XSSFWorkbook wb = new XSSFWorkbook();
//        //创建一张表
//        Sheet sheet = wb.createSheet("学生信息模板");
//        //创建第一行，起始为0
//        Row titleRow = sheet.createRow(0);
//        //第一列
//        titleRow.createCell(0).setCellValue("学号");
//        titleRow.createCell(1).setCellValue("学生姓名");
//        titleRow.createCell(2).setCellValue("手机号码");
//        titleRow.createCell(3).setCellValue("电子邮箱");
//        titleRow.createCell(4).setCellValue("专业（软0计1）");
//
//        return wb;
//    }


}
