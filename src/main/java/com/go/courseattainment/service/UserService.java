package com.go.courseattainment.service;

import com.go.courseattainment.entities.User;
import com.go.courseattainment.vo.LoginVo;
import com.go.courseattainment.vo.RespBean;
import com.go.courseattainment.vo.UserRegisterVo;
import com.go.courseattainment.vo.updatePwVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-03-26 16:24
 **/
@Service
public interface UserService {

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return User
     */
    User getUserByUsername(String username);

    /**
     * 获取当前用户
     *
     * @return
     */
    User getCurrentUser();

    /**
     * 用户注册
     * @param userRegisterVo
     * @return
     */
    RespBean userRegister(UserRegisterVo userRegisterVo);

    /**
     * 登录功能
     * @param loginVo
     * @return RespBean
     */
    RespBean login(LoginVo loginVo, HttpServletResponse response);


    /**
     * 获取验证码
     * @param request
     * @param response
     * @return
     */
    RespBean getVerCode(HttpServletRequest request,HttpServletResponse response);


    /**
     * 更新用户密码
     * @param updatePwVo
     * @return
     */
    RespBean updatePw(updatePwVo updatePwVo);


    /**
     * 上传头像
     * @param file
     * @return RespBean
     */
    RespBean updatePhoto(MultipartFile file);


    /**
     * 获得所有老师信息
     * @return RespBean
     */
    RespBean getAllTeachers();


    /**
     * 获得所有任课老师的姓名
     * @return RespBean
     */
    RespBean getAllCourseTeacherNames();

}
