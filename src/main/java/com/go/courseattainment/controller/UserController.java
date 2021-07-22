package com.go.courseattainment.controller;

import com.go.courseattainment.accessctro.RoleContro;
import com.go.courseattainment.enums.RespBeanEnum;
import com.go.courseattainment.enums.RoleEnum;
import com.go.courseattainment.service.UserService;
import com.go.courseattainment.vo.RespBean;
import com.go.courseattainment.vo.UserRegisterVo;
import com.go.courseattainment.vo.updatePwVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * @program: CourseAttainment
 * @description 用户控制类
 * @author: 不会编程的派大星
 * @create: 2021-03-27 19:57
 **/
@Controller
@RequestMapping("course/user")
@Slf4j
@Api(tags = "用户端-用户接口")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("用户注册")
    @RoleContro(role = RoleEnum.SUPPER_ADMIN)
    public RespBean  register(@Valid UserRegisterVo userRegisterVo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("必填项未填！");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return userService.userRegister(userRegisterVo);
    }



    @RequestMapping(value = "/updatePw",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("更改密码")
    public RespBean updatePassword(@Valid updatePwVo updatePwVo,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("必填项未填！");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return userService.updatePw(updatePwVo);
    }



    @RequestMapping(value = "/uploadPhoto",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("上传头像")
    public RespBean uploadPhoto(MultipartFile file){
        return userService.updatePhoto(file);
    }



    @RequestMapping(value = "/getAllTeachers",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("获取所有老师")
    public RespBean getAllTeachers(){
        return userService.getAllTeachers();
    }


}
