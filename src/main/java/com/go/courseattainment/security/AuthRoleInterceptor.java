package com.go.courseattainment.security;

import com.alibaba.fastjson.JSON;

import com.go.courseattainment.accessctro.RoleContro;
import com.go.courseattainment.entities.User;
import com.go.courseattainment.enums.RespBeanEnum;
import com.go.courseattainment.service.UserService;
import com.go.courseattainment.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName AuthRoleInterceptor
 * @Author zty
 * @Date 19-4-22 下午7:47
 * @Description 权限验证
 */
@Slf4j
@Service
public class AuthRoleInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(".....................");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String json = JSON.toJSONString(RespBean.ERROR(RespBeanEnum.AUTHENTICATION_ERROR));
        User user = userService.getCurrentUser();
        if (user == null) {
            return true;
        }
        log.info("============执行权限验证============");
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            RoleContro roleContro = handlerMethod.getMethodAnnotation(RoleContro.class);
            if (roleContro == null) {
                return true;
            }
            Integer roleValue = roleContro.role().getValue();
            Integer userValue = user.getRole();
            log.info("RoleValue:{},userRole:{}", roleValue, userValue);
            if (userValue >= roleValue) {
                return true;
            } else {
                json = JSON.toJSONString(RespBean.ERROR(RespBeanEnum.PERMISSION_DENNY));
                log.info("============权限不足===============");
            }
        }
        response.getWriter().append(json);
        return false;
    }

}
