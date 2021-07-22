package com.go.courseattainment.config;



import com.go.courseattainment.security.AuthRoleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zty
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

   @Autowired
    private AuthRoleInterceptor authRoleInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authRoleInterceptor);
    }

}
