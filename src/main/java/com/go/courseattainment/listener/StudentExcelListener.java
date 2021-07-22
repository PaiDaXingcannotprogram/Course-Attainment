package com.go.courseattainment.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.go.courseattainment.ExcelForm.StudentExcelForm;
import com.go.courseattainment.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-03-18 14:28
 **/
public class StudentExcelListener extends AnalysisEventListener<StudentExcelForm> {



    private static final int BATCH_COUNT = 5;
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentExcelListener.class);
    List<StudentExcelForm> stuList = new ArrayList<>();

    private StudentService studentService;
    public StudentExcelListener(StudentService studentService) {
        this.studentService = studentService;
    }

    public StudentExcelListener() {
    }

    @Override
    public void invoke(StudentExcelForm stu, AnalysisContext analysisContext) {
        LOGGER.info("解析到一条数据："+ JSON.toJSONString(stu));
        stuList.add(stu);
        if(stuList.size() >= BATCH_COUNT){
            saveData();
            stuList.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
        LOGGER.info("所有数据解析完成！");
    }

    public void saveData(){
        System.out.println("共"+stuList.size()+"条数据,开始解析并存到储数据库！");
        studentService.addBatchStus(stuList);
        System.out.println("数据解析中...");
    }


}



