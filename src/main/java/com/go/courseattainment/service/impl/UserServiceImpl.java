package com.go.courseattainment.service.impl;

import com.go.courseattainment.entities.PhotoUpload;
import com.go.courseattainment.entities.User;
import com.go.courseattainment.enums.RespBeanEnum;
import com.go.courseattainment.mapper.UserMapper;
import com.go.courseattainment.security.JwtProperties;
import com.go.courseattainment.security.JwtUserDetailServiceImpl;
import com.go.courseattainment.service.UserService;
import com.go.courseattainment.utils.JwtTokenUtil;
import com.go.courseattainment.utils.RedisUtil;
import com.go.courseattainment.utils.UploadHeadUtil;
import com.go.courseattainment.vo.*;
import com.wf.captcha.SpecCaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.Line;
import java.io.Serializable;
import java.util.*;

/**
 * @program: CourseAttainment
 * @description 用户服务类
 * @author: 不会编程的派大星
 * @create: 2021-03-27 14:45
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private JwtUserDetailServiceImpl jwtUserDetailService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UploadHeadUtil uploadHeadUtil;

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }


    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(authentication.toString());
        String name = authentication.getName();
        String Key = "anonymousUser";
        if(!name.equals(Key)){
            return getUserByUsername(name);
        }
        return null;
    }


    @Override
    public RespBean userRegister(UserRegisterVo userRegisterVo) {
        //如果验证码出错，则不执行以下过程
        User userByUsername = userMapper.getUserByUsername(userRegisterVo.getUsername());
        if(userByUsername != null){
            return RespBean.ERROR(RespBeanEnum.USER_HAS_REPEAT);
        }
        User user = new User();
        BeanUtils.copyProperties(userRegisterVo,user);
        user.setPassword(passwordEncoder.encode(jwtProperties.getDefaultPassword()));
        log.info("用户信息"+user);
        int result = userMapper.insertIntoUser(user);
        if(result != 1){
            return RespBean.ERROR(RespBeanEnum.REGISTER_ERROR);
        }
        return RespBean.SUCCESS("注册成功");
    }


    @Override
    public RespBean login(LoginVo loginVo, HttpServletResponse response) {
        String redisCode = (String) redisUtil.get(loginVo.getVerKey());
        log.info("取得验证码"+redisCode);
        if(redisCode == null){
            return RespBean.ERROR(RespBeanEnum.CODE_NOT_EXIST);
        }
        /**
         * 判断验证码
         */
        if(loginVo.getVerCode()==null || !redisCode.equals(loginVo.getVerCode().trim().toLowerCase())){
            return RespBean.ERROR(RespBeanEnum.CAPTCHA_IS_ERROR);
        }
        User user = userMapper.getUserByUsername(loginVo.getUsername());
        if(user == null){
            return RespBean.ERROR(RespBeanEnum.USER_NOT_EXIST);
        }
        UserDetails userDetails = jwtUserDetailService.loadUserByUsername(loginVo.getUsername());
        if (!(new BCryptPasswordEncoder().matches(loginVo.getPassword(),userDetails.getPassword()))) {
            return RespBean.ERROR(RespBeanEnum.LOGIN_ERROR);
        }
        Authentication token = new UsernamePasswordAuthenticationToken(loginVo.getUsername(),
                loginVo.getPassword(),
                userDetails.getAuthorities());
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String realToken = jwtTokenUtil.generateToken(userDetails);
        response.setHeader(jwtProperties.getTokenName(),realToken);
        Map<String, Serializable>  map = new HashMap<>();
        map.put("name",user.getName());
        map.put("role",user.getRole());
        map.put("token",realToken);

        return RespBean.SUCCESS(map);
    }


    @Override
    public RespBean getVerCode(HttpServletRequest request, HttpServletResponse response) {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        String verCode = specCaptcha.text().toLowerCase();
        log.info(verCode);
        String key = UUID.randomUUID().toString();

        redisUtil.set(key,verCode,300);
        Map<String,Serializable> map = new HashMap<>(16);
        map.put("key",key);
        map.put("image",specCaptcha.toBase64());
        return RespBean.SUCCESS(map);
    }


    @Override
    public RespBean updatePw(updatePwVo updatePwVo) {
        User user = getCurrentUser();
        if(!user.getUsername().equals(updatePwVo.getUsername())){
            return RespBean.ERROR(RespBeanEnum.IS_NOT_PERSONAL_OPERATION);
        }
        UserDetails userDetails = jwtUserDetailService.loadUserByUsername(updatePwVo.getUsername());
        if(!new BCryptPasswordEncoder().matches(userDetails.getPassword(),updatePwVo.getOldPassword())){
            return RespBean.ERROR(RespBeanEnum.PASSWORD_ERROR);
        }
        User updateUser = userMapper.getUserByUsername(updatePwVo.getUsername());
        updatePwVo.setNewPassword(new BCryptPasswordEncoder().encode(updatePwVo.getNewPassword()));
        if(userMapper.updateByPrimaryKey(updateUser)==1){
            return RespBean.SUCCESS("修改成功");
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }


    @Override
    public RespBean updatePhoto(MultipartFile file) {
        User user = getCurrentUser();
        log.info("开始上传图片");
        PhotoUpload photoUpload = uploadHeadUtil.fileUpload(file);
        user.setPhoto(photoUpload.getPhotoUrl());
        userMapper.updateByPrimaryKey(user);
        return RespBean.SUCCESS("上传成功");
    }


    @Override
    public RespBean getAllTeachers() {
        List<User> users = userMapper.selectByRole("0");
        List<TeacherVO> teacherVos = new ArrayList<>();
        for(User user: users){
            TeacherVO teacherVO = new TeacherVO();
            BeanUtils.copyProperties(user,teacherVO);
            teacherVos.add(teacherVO);
        }
        return RespBean.SUCCESS(teacherVos);
    }

    @Override
    public RespBean getAllCourseTeacherNames() {
        List<String> teacherNames = userMapper.selectAllTeacherNames();

        if(teacherNames != null){
            return RespBean.SUCCESS(teacherNames);
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }
}
