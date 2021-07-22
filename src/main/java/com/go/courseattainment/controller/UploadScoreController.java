package com.go.courseattainment.controller;

import com.go.courseattainment.accessctro.RoleContro;
import com.go.courseattainment.enums.RespBeanEnum;
import com.go.courseattainment.enums.RoleEnum;
import com.go.courseattainment.form.UploadScoreForm;
import com.go.courseattainment.service.UploadScoreService;
import com.go.courseattainment.vo.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-24 21:43
 **/
@RestController
@RequestMapping("/course/uploadScore")
@CrossOrigin
@Slf4j
@Api(tags = "教师端-上传模块小题成绩")
public class UploadScoreController {

    @Autowired
    private UploadScoreService uploadScoreService;



    @GetMapping("/getName")
    @ApiOperation("根据输入的学生学号获取学生姓名")
    @RoleContro(role = RoleEnum.USER)
    public RespBean getNameByNo(String stuNo){
       if(stuNo.isEmpty()){
            log.info("必填项未填！");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
       }
        return uploadScoreService.getStuNameByNo(stuNo);
  }



  @PostMapping("/upload")
  @ApiOperation("上传学生模块小题成绩")
  @RoleContro(role = RoleEnum.USER)
  public RespBean uploadStuScore(List<UploadScoreForm> stuScoreList){
      if(stuScoreList.isEmpty()){
          log.info("必填项未填！");
          return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
      }
      return uploadScoreService.uploadStuScore(stuScoreList);
  }




  @PostMapping("/update")
  @ApiOperation("更新、编辑学生模块小题成绩")
  @RoleContro(role = RoleEnum.USER)
  public RespBean updateStuScore(@Valid UploadScoreForm uploadScoreForm, BindingResult bindingResult){
      if(bindingResult.hasErrors()){
          log.info("必填项未填！");
          return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
      }
      return uploadScoreService.updateStuScore(uploadScoreForm);
  }



  @GetMapping("/delete")
  @ApiOperation("根据主键PrimaryKey删除学生模块小题成绩")
  @RoleContro(role = RoleEnum.USER)
  public RespBean deleteStuScore(Integer uploadScoreId){
      if(uploadScoreId == null){
          log.info("必填项未填！");
          return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
      }
      return uploadScoreService.deleteStuScore(uploadScoreId);
  }




  @GetMapping("/getScores")
  @ApiOperation("获取上传的学生模块小题分数信息")
  @RoleContro(role = RoleEnum.USER)
  public RespBean getStuScores(){
        return uploadScoreService.getAllScoresFromCache();
  }





}
