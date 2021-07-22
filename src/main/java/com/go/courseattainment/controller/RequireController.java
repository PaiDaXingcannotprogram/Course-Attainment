package com.go.courseattainment.controller;

import com.go.courseattainment.accessctro.RoleContro;
import com.go.courseattainment.entities.GradeRequire;
import com.go.courseattainment.enums.RespBeanEnum;
import com.go.courseattainment.enums.RoleEnum;
import com.go.courseattainment.form.DetailRequireForm;
import com.go.courseattainment.form.GradeRequireForm;
import com.go.courseattainment.form.UpdateDetailRequireForm;
import com.go.courseattainment.form.UpdateGradeRequireForm;
import com.go.courseattainment.service.RequireService;
import com.go.courseattainment.vo.DetailRequireVo;
import com.go.courseattainment.vo.RespBean;
import com.rabbitmq.client.AMQP;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.PublicKey;

/**
 * @program: CourseAttainment
 * @description 毕业要求指标点控制类
 * @author: 不会编程的派大星
 * @create: 2021-03-29 21:07
 **/
@RestController
@RequestMapping("course/require")
@CrossOrigin
@Slf4j
@Api(tags = "专业负责人端-毕业要求指标点")
public class RequireController {

    @Autowired
    private RequireService requireService;


    @GetMapping("/getFirst")
    @ApiOperation("获取当前专业所有排序好的一级指标点")
    @RoleContro(role = RoleEnum.ADMIN)
    public RespBean getAllGradeRequires(){
        return requireService.getAllGradeRequires();
    }


    @PostMapping("/addGradeRequire")
    @ApiOperation("添加一级指标点")
    @RoleContro(role = RoleEnum.ADMIN)
    public RespBean addGradeRequire(@Valid GradeRequireForm gradeRequireForm,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("必填项未填！");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return requireService.addGradeRequire(gradeRequireForm);

    }


    @PostMapping("/updateGradeRequire")
    @ApiOperation("修改一级指标点")
    @RoleContro(role = RoleEnum.ADMIN)
    public RespBean updateGradeRequire(@Valid UpdateGradeRequireForm updateGradeRequireForm,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("必填项未填！");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return requireService.updateGradeRequire(updateGradeRequireForm);
    }


    @GetMapping("/splitGradeRequire")
    @ApiOperation("拆分一级指标点")
    @RoleContro(role = RoleEnum.ADMIN)
    @ApiImplicitParam(name = "gradeRequireNo",value = "对应的一级指标点序号")
    public RespBean splitGradeRequire(Integer gradeRequireNo){
        if(gradeRequireNo == null){
            log.info("必填项未填！");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return requireService.splitGradeRequire(gradeRequireNo);
    }

    @GetMapping("/getSecond")
    @ApiOperation("获取当前专业所有的二级指标点")
    @RoleContro(role = RoleEnum.ADMIN)
    public RespBean getAllDetailRequires(){
        return requireService.getAllDetailRequires();
    }


    @PostMapping("/addDetailRequire")
    @ApiOperation("添加二级指标点")
    @RoleContro(role = RoleEnum.ADMIN)
    public RespBean addDetailRequire(@Valid DetailRequireForm detailRequireForm,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("必填项未填！");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return requireService.addDetailRequire(detailRequireForm);
    }


    @PostMapping("/updateDetailRequire")
    @ApiOperation("修改二级指标点")
    @RoleContro(role = RoleEnum.ADMIN)
    public RespBean updateDetailRequire(@Valid UpdateDetailRequireForm updateDetailRequireForm,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("必填项未填！");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return requireService.updateDetailRequire(updateDetailRequireForm);
    }



    @PostMapping("/deleteDetailRequire")
    @ApiOperation("删除二级指标点")
    @RoleContro(role = RoleEnum.ADMIN)
    @ApiImplicitParam(name = "detailRequireId",value = "二级指标点PrimaryKey")
    public RespBean deleteDetailRequire(Integer detailRequireId){
        if(detailRequireId == null){
            log.info("必填项未填！");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return requireService.deleteDetailRequire(detailRequireId);
    }


}
