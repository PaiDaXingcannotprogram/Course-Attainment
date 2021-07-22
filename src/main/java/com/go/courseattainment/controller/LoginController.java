package com.go.courseattainment.controller;

import com.go.courseattainment.enums.RespBeanEnum;
import com.go.courseattainment.service.UserService;
import com.go.courseattainment.vo.LoginVo;
import com.go.courseattainment.vo.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.java2d.pipe.RegionSpanIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @program: CourseAttainment
 * @description 登录控制类
 * @author: 不会编程的派大星
 * @create: 2021-03-28 09:51
 **/
@Controller
@CrossOrigin
@Slf4j
@RequestMapping("course/login")
@Api(tags = "用户端-用户登录")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/dologin",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("登录")
    public RespBean doLogin(@Valid LoginVo loginVo, HttpServletResponse response, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("必填项未填");
            return RespBean.ERROR(RespBeanEnum.PARAMETER_ERROR);
        }
        return userService.login(loginVo, response);
    }



    @RequestMapping(value = "/getVerCode",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("获取图片验证码")
    public RespBean getCaptcha(HttpServletRequest request,HttpServletResponse response){
        return userService.getVerCode(request,response);
    }

}
