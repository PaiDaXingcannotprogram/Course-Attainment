package com.go.courseattainment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 不会编程的派大星
 */
@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
@EnableTransactionManagement //开启事务
public class CourseattainmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseattainmentApplication.class, args);
    }

}
