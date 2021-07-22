package com.go.courseattainment.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.go.courseattainment.service.CourseSchemeService;
import com.go.courseattainment.vo.SeCourseSchemeExcelS1Vo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.List;

/**
 * @program: CourseAttainment
 * @description读取选课方案
 * 计科选课方案excel读取监听器
 * @author: 不会编程的派大星
 * @create: 2021-04-02 17:29
 **/
public class SeCourseSchemeExcelS1Listener extends AnalysisEventListener<SeCourseSchemeExcelS1Vo> {


    private static final int BATCH_COUNT = 5;
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentExcelListener.class);
    List<SeCourseSchemeExcelS1Vo> courseVoList = new ArrayList<>();


    private CourseSchemeService courseSchemeService;
    private Integer majorId;
    public SeCourseSchemeExcelS1Listener(CourseSchemeService courseSchemeService, Integer majorId){
        this.courseSchemeService = courseSchemeService;
        this.majorId = majorId;
    }

    @Override
    public void invoke(SeCourseSchemeExcelS1Vo courseSchemeVo, AnalysisContext analysisContext) {
        LOGGER.info("解析到一条数据："+ JSON.toJSONString(courseSchemeVo));
        courseVoList.add(courseSchemeVo);
        if(courseVoList.size() >= BATCH_COUNT){
            saveData();
            courseVoList.clear();
        }
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
        LOGGER.info("所有数据解析完成！");
    }


    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        LOGGER.info("解析失败");
        throw exception;
    }


    public void saveData(){
        System.out.println("共"+courseVoList.size()+"条数据,开始解析并存到储数据库！");
        courseSchemeService.saveSeCourseSchemesS1(courseVoList,majorId);
        System.out.println("数据解析中...");
    }
}
