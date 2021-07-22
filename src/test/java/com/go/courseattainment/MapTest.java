package com.go.courseattainment;

import com.go.courseattainment.mapper.RequireMapper;
import com.go.courseattainment.mapper.StudentMapper;
import com.go.courseattainment.service.RequireService;
import com.go.courseattainment.service.StudentService;
import com.go.courseattainment.service.TeacherPlanService;
import com.go.courseattainment.service.UserService;
import com.go.courseattainment.vo.RespBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-03-31 20:36
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MapTest {

    @Autowired
    private RequireMapper requireMapper;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private RequireService requireService;

    @Autowired
    private UserService userService;

    @Qualifier("TPlanServiceImpl")
    @Autowired
    private TeacherPlanService tPlanService;


    @Test
   public void  test() {

        RespBean respBean = tPlanService.finishCalculation(1);
        System.out.println(respBean.getCode());
    }


}

