package com.go.courseattainment.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.go.courseattainment.vo.RespBean;
import com.go.courseattainment.enums.RespBeanEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-03-15 15:36
 **/
@Component
public class MyAccessdeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        String errorMsg = objectMapper.writeValueAsString(RespBean.ERROR(RespBeanEnum.WITHOUT_AUTH));
        out.write(errorMsg);
      // out.write("{\"status\": \"error\", \"msg\":\"权限不足请联系管理员!!\"}");
        out.flush();
        out.close();
    }
}
