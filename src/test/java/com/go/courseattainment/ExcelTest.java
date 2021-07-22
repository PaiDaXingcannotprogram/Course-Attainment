package com.go.courseattainment;

import com.go.courseattainment.service.CourseSchemeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-04-05 10:37
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class ExcelTest {

    @Autowired
    private CourseSchemeService courseSchemeService;

    @Test
    public void testSheet() {

    }
}
