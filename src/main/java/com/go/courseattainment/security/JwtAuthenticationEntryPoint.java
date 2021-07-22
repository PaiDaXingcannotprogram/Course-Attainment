package com.go.courseattainment.security;

import com.alibaba.fastjson.JSON;
import com.go.courseattainment.vo.RespBean;
import com.go.courseattainment.enums.RespBeanEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @program: CourseAttainment
 * @description 自定义拦截器类，用于拦截未进行身份认证的情况
 * @author: 不会编程的派大星
 * @create: 2021-03-26 12:46
 **/
@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
       httpServletResponse.setHeader("Access_Control_Allow_Origin","*");
       httpServletResponse.setCharacterEncoding("UTF-8");
       httpServletResponse.setContentType("text/html; charset=utf-8");
        RespBean respBean = RespBean.ERROR(RespBeanEnum.AUTHENTICATION_ERROR);
        log.info("需要身份认证："+respBean);
        httpServletResponse.getWriter().append(JSON.toJSONString(respBean));
    }
}
