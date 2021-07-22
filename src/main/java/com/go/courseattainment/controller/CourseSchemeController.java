package com.go.courseattainment.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.go.courseattainment.accessctro.RoleContro;
import com.go.courseattainment.enums.RespBeanEnum;
import com.go.courseattainment.enums.RoleEnum;
import com.go.courseattainment.listener.JkCourseSchemeExcelS1Listener;
import com.go.courseattainment.listener.JkCourseSchemeExcelS2Listener;
import com.go.courseattainment.listener.SeCourseSchemeExcelS1Listener;
import com.go.courseattainment.listener.SeCourseSchemeExcelS2Listener;
import com.go.courseattainment.service.CourseSchemeService;
import com.go.courseattainment.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-04-02 19:07
 **/

@Controller
@RequestMapping("course/courseScheme")
@CrossOrigin
@Slf4j
@Api(tags = "专业负责人端-矩阵管理-录入专业选课方案")
public class CourseSchemeController {

    @Autowired
    private CourseSchemeService courseSchemeService;


    @RequestMapping(value = "/saveScheme",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("读取专业选课方案")
    @RoleContro(role = RoleEnum.ADMIN)
    public RespBean readCourseScheme(MultipartFile file,Integer majorId) throws IOException {
        if(file == null){
            return RespBean.ERROR(RespBeanEnum.FILE_BLANK);
        }
        ExcelReader excelReader = null;
        //监听器，计科和软工
        ReadListener listenerSheet1 = null;
        ReadListener listenerSheet2 = null;
        //模板类，计科和软工
        Class clazzSheet1 = null;
        Class clazzSheet2 = null;

            try {
                excelReader = EasyExcelFactory.read(file.getInputStream()).build();

                //根据专业Id选取不同的的head和listener
                if(majorId == 0){
                    listenerSheet1 = new SeCourseSchemeExcelS1Listener(courseSchemeService, majorId);
                    listenerSheet2 = new SeCourseSchemeExcelS2Listener(courseSchemeService,majorId);
                    clazzSheet1 = SeCourseSchemeExcelS1Vo.class;
                    clazzSheet2 = SeCourseSchemeExcelS2Vo.class;

                }else if(majorId == 1){
                    listenerSheet1 = new JkCourseSchemeExcelS1Listener(courseSchemeService, majorId);
                    listenerSheet2 = new JkCourseSchemeExcelS2Listener(courseSchemeService, majorId);
                    clazzSheet1 = JkCourseSchemeExcelS1Vo.class;
                    clazzSheet2 = JkCourseSchemeExcelS2Vo.class;
                }

                ReadSheet sheet1 = EasyExcelFactory.readSheet(1).head(clazzSheet1)
                        .registerReadListener(listenerSheet1).headRowNumber(3).build();

                ReadSheet sheet2 = EasyExcelFactory.readSheet(2).head(clazzSheet2)
                        .registerReadListener(listenerSheet2).headRowNumber(3).build();
                excelReader.read(sheet1, sheet2);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (excelReader != null) {
                    excelReader.finish();
                }
            }
        return RespBean.SUCCESS();
    }
}
