package com.go.courseattainment.security;

import com.go.courseattainment.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: CourseAttainment
 * @description 自定义拦截器，用于进行自定义用户身份验证
 * @author: 不会编程的派大星
 * @create: 2021-03-26 12:52
 **/
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailServiceImpl userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private String tokenHeader = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (httpServletRequest.getMethod().equals("OPTIONS")) {
            //TODO，这里是解决前后端对接时的跨域问题
            log.info("浏览器的请求预处理");
            httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
            httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE");
            httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
            httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With,Content-Type,Accept,Authorization,token,Cookie");
            return;
        } else {
            String requestUrl = httpServletRequest.getRequestURI();
            log.info("requestURL: {}", requestUrl);
            String authToken = httpServletRequest.getHeader(this.tokenHeader);
            log.info("authToken:"+authToken);

            String workNum = jwtTokenUtil.getUsernameFromToken(authToken);

            log.info("checking authentication for user " + workNum);

            //当token中的username不为空时进行验证token是否是有效的token
            if (workNum != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                log.info("token中username不为空，并且Context中的认证为空，进行token验证");
                //TODO,从数据库得到带有密码的完整user信息
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(workNum);
                log.info("加载userDetails:{}", userDetails.getUsername());
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    log.info("authenticated user " + workNum + ", setting security context");
                    //将authentication放入SecurityContextHolder中
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(httpServletRequest, httpServletResponse);

        }
    }
}
