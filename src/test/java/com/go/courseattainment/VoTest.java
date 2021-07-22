package com.go.courseattainment;

import com.go.courseattainment.entities.ModuleQuestion;
import com.go.courseattainment.mapper.ModuleQuestionMapper;
import com.go.courseattainment.service.ModuleService;
import com.go.courseattainment.vo.ModuleQuestionVo;
import com.go.courseattainment.vo.RespBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-23 20:48
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class VoTest {

    @Autowired
    private ModuleQuestionMapper moduleQuestionMapper;



    @Test
    public void test(){
        List<ModuleQuestionVo> questionVos = new ArrayList<>();
        List<ModuleQuestion> moduleQuestions = moduleQuestionMapper.selectAllQuestionsByOrder();

        for(ModuleQuestion moduleQuestion : moduleQuestions){
            ModuleQuestionVo moduleQuestionVo = new ModuleQuestionVo();
            BeanUtils.copyProperties(moduleQuestion,moduleQuestionVo);
            questionVos.add(moduleQuestionVo);
        }
        Integer initValue = moduleQuestions.size()*4/3+1;
        StringBuilder builder = new StringBuilder();
        Map<Integer, List<String>> map = new HashMap<>(initValue);
        for (ModuleQuestion mq : moduleQuestions){
            if(builder.indexOf(mq.getQuestionNo().toString()) > -1){
                map.get(mq.getQuestionNo()).add(mq.getDetailRequireId());
                continue;
            }
            else{
                List<String> list = new ArrayList<>();
                list.add(mq.getDetailRequireId());
                map.put(mq.getQuestionNo(),list);
                builder.append(mq.getQuestionNo().toString());
            }
        }

        for(ModuleQuestionVo vo : questionVos){
            vo.setDetailRequireIds(map.get(vo.getQuestionNo()));
        }

        System.out.println(questionVos);
    }
}
