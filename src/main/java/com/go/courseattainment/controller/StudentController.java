package com.go.courseattainment.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSON;
import com.go.courseattainment.ExcelForm.StudentExcelForm;
import com.go.courseattainment.accessctro.RoleContro;
import com.go.courseattainment.enums.RespBeanEnum;
import com.go.courseattainment.enums.RoleEnum;
import com.go.courseattainment.form.UpdateStudentForm;
import com.go.courseattainment.listener.StudentExcelListener;
import com.go.courseattainment.service.StudentService;
import com.go.courseattainment.vo.RespBean;
import com.go.courseattainment.vo.StudentPageVo;
import com.go.courseattainment.form.StudentForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: CourseAttainment
 * @description 学生管理模块控制类
 * @author: 不会编程的派大星
 * @create: 2021-04-15 14:22
 **/
@RestController
@RequestMapping("course/student")
@CrossOrigin
@Slf4j
@Api(tags = "专业负责人端-学生管理")
public class StudentController {

    @Autowired
    private StudentService studentService;



//    @PostMapping("/downloadTemplate")
//    @ApiOperation("下载模板")
//    public RespBean downloadTemplate(HttpServletRequest request,HttpServletResponse response) {
//
//        return studentService.uploadTemplate(request,response);
//
//        }




    @PostMapping("/uploadTemplate")
    @ApiOperation("上传模板")
    @RoleContro(role = RoleEnum.ADMIN)
    public RespBean uploadTemplate(MultipartFile file) throws IOException {
      if(file == null){
          log.info("上传失败");
          return RespBean.ERROR(RespBeanEnum.FILE_BLANK);
      }
        EasyExcelFactory.read(file.getInputStream(),
                StudentExcelForm.class,
                new StudentExcelListener(studentService))
                .sheet().doRead();
      return RespBean.SUCCESS("上传成功");
    }


    @PostMapping("/addOneStu")
    @ApiOperation("添加单个学生信息")
    @RoleContro(role = RoleEnum.ADMIN)
    public RespBean addOneStudent(@Valid StudentForm studentForm,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("必填项未填！");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return studentService.addOneStu(studentForm);
    }



    @PostMapping("/updateStu")
    @ApiOperation("更新学生信息")
    @RoleContro(role = RoleEnum.ADMIN)
    public RespBean updateStudent(@Valid UpdateStudentForm updateStudentForm,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("必填项未填！");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return studentService.updateStudent(updateStudentForm);
    }




    @PostMapping("/deleteStu")
    @ApiOperation("删除学生信息")
    @RoleContro(role = RoleEnum.ADMIN)
    @ApiImplicitParam(name = "stuId",value = "主键PrimaryKey，用于条件查找")
    public RespBean deleteStudent(Integer stuId){
        if(stuId == null){
            log.info("必填项未填！");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return studentService.deleteOne(stuId);
    }



    @GetMapping("/getByStuNo")
    @ApiOperation("通过学号查找学生信息")
    @RoleContro(role = RoleEnum.ADMIN)
    public RespBean getStudentByStuNo(String stuNo){
        if(stuNo.isEmpty()){
            log.info("必填项未填！");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return studentService.getStuByNo(stuNo);
    }



    @GetMapping("/getByName")
    @ApiOperation("通过姓名查找学生信息")
    @RoleContro(role = RoleEnum.ADMIN)
    public RespBean getStudentByName(String stuName,Integer gradeId){
        if(stuName.isEmpty()){
            log.info("必填项未填！");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return studentService.getStuByName(stuName,gradeId);
    }


    @GetMapping("/getAll")
    @ApiOperation("获取所有学生信息")
    @RoleContro(role = RoleEnum.ADMIN)
    public RespBean getCurrentStudents(Integer gradeId){
        return studentService.getCurrentMajorAndGradeStus(gradeId);
    }



    @GetMapping("/getAllByPage")
    @ApiOperation("分页获取所有学生信息")
    @RoleContro(role = RoleEnum.ADMIN)
    public RespBean getCurrentStudentsByPage(@Valid StudentPageVo studentPageVo,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("必填项未填！");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return studentService.findStudentByPage(studentPageVo);
    }





}
