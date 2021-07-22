package com.go.courseattainment.service.impl;

import com.go.courseattainment.entities.Module;
import com.go.courseattainment.entities.UploadScore;
import com.go.courseattainment.enums.RespBeanEnum;
import com.go.courseattainment.form.CalculateModuleSupportedScoreForm;
import com.go.courseattainment.form.UploadScoreForm;
import com.go.courseattainment.mapper.ModuleMapper;
import com.go.courseattainment.mapper.StudentMapper;
import com.go.courseattainment.mapper.UploadScoreMapper;
import com.go.courseattainment.service.ModuleQuestionService;
import com.go.courseattainment.service.UploadScoreService;
import com.go.courseattainment.utils.RedisUtil;
import com.go.courseattainment.vo.RespBean;
import com.go.courseattainment.vo.UploadScoreVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-24 21:46
 **/
@Service
@Slf4j
public class UploadScoreServiceImpl implements UploadScoreService {

    private static final String cacheName = "stuScoreList";

    @Autowired
    private UploadScoreMapper uploadScoreMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ModuleQuestionService moduleQuestionService;

    @Autowired
    private ModuleMapper moduleMapper;

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public RespBean getStuNameByNo(String stuNo) {
        String stuNameByNo = studentMapper.selectStuNameByNo(stuNo);
        if(stuNameByNo.isEmpty()){
            return RespBean.ERROR(RespBeanEnum.STU_NOT_EXIST);
        }
        return  RespBean.SUCCESS(stuNameByNo);
    }


    @Override
    public RespBean uploadStuScore(List<UploadScoreForm> stuScoreList) {
        List<String> uploadErrorList = new ArrayList<>();
        Map<String, Object> stuScoreMap = new HashMap<>(stuScoreList.size());
        for(UploadScoreForm UF : stuScoreList ){
            UploadScoreVo uploadScoreVo = new UploadScoreVo();
            BeanUtils.copyProperties(UF,uploadScoreVo);
            stuScoreMap.put(uploadScoreVo.getStuName(),uploadScoreVo);

            if(uploadSingleStuScore(UF)){
                continue;
            }else {
                uploadErrorList.add(UF.getStuName());
            }
        }

        setStuScoreCache(stuScoreMap);

        if(uploadErrorList.isEmpty()){
            return RespBean.SUCCESS("上传成功");
        }
        return RespBean.ERROR(RespBeanEnum.UPLOAD_SCORE_ERROR);
    }


    @Override
    public RespBean updateStuScore(UploadScoreForm uploadScoreForm) {
        updateStuScoreCache(uploadScoreForm);
        HashMap<Integer, BigDecimal> stuScore = uploadScoreForm.getStuScore();
        List<CalculateModuleSupportedScoreForm> supportedScoreForms =
                moduleQuestionService.CalculateTotalModuleSupportedScore(uploadScoreForm.getModuleId());
        Module module = moduleMapper.selectByPrimaryKey(uploadScoreForm.getModuleId());
        Iterator<Integer> it = stuScore.keySet().iterator();

        for(CalculateModuleSupportedScoreForm moduleForm: supportedScoreForms){
            UploadScore US = new UploadScore();
            BigDecimal actualSupportedScore = new BigDecimal(0.0);
            while (it.hasNext()){
                if(moduleForm.getDetailRequireId().equals(moduleQuestionService.getDetailIdByNo(it.next()))){
                    actualSupportedScore.add(stuScore.get(it.next()));
                }
            }
            BeanUtils.copyProperties(uploadScoreForm,US);
            BigDecimal moduleScore = actualSupportedScore.divide(moduleForm.getDetailRequireIdTotalScore())
                    .multiply(module.getCourseProportion());
            US.setModuleScore(moduleScore);
            US.setDetailRequireId(moduleForm.getDetailRequireId());
            int result = uploadScoreMapper.updateSetScore(US);
            if(result == 0){
                return RespBean.ERROR(RespBeanEnum.UPLOAD_SCORE_ERROR);
            }
        }
        return RespBean.SUCCESS("修改成功");
    }


    @Override
    public RespBean deleteStuScore(Integer uploadScoreId) {
        int result = uploadScoreMapper.deleteFromScore(uploadScoreId);
        if(result != 0){
            return RespBean.SUCCESS("删除成功");
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }


    @Override
    public RespBean getAllScoresFromCache() {
        Map<Object, Object> scoreMap = redisUtil.hmget(cacheName);
        List<UploadScoreVo> scoreList = new ArrayList<>();
        Iterator<Object> it = scoreMap.keySet().iterator();
        while (it.hasNext()){
            UploadScoreVo vo = (UploadScoreVo) scoreMap.get(it.next());
            scoreList.add(vo);
        }
        if(scoreList.isEmpty()){
            return RespBean.ERROR(RespBeanEnum.ERROR);
        }
        return RespBean.SUCCESS(scoreList);
    }

    public boolean uploadSingleStuScore(UploadScoreForm uploadScoreForm){
        HashMap<Integer, BigDecimal> stuScore = uploadScoreForm.getStuScore();
        List<CalculateModuleSupportedScoreForm> supportedScoreForms =
                moduleQuestionService.CalculateTotalModuleSupportedScore(uploadScoreForm.getModuleId());
        Module module = moduleMapper.selectByPrimaryKey(uploadScoreForm.getModuleId());
        Iterator<Integer> it = stuScore.keySet().iterator();

        for(CalculateModuleSupportedScoreForm moduleForm: supportedScoreForms){
            UploadScore uploadScore = new UploadScore();
            BigDecimal actualSupportedScore = new BigDecimal(0.0);
            while (it.hasNext()){
                if(moduleForm.getDetailRequireId().equals(moduleQuestionService.getDetailIdByNo(it.next()))){
                    actualSupportedScore.add(stuScore.get(it.next()));
                }
            }
            BeanUtils.copyProperties(uploadScoreForm,uploadScore);
            BigDecimal moduleScore = actualSupportedScore.divide(moduleForm.getDetailRequireIdTotalScore())
                    .multiply(module.getCourseProportion());
            uploadScore.setModuleScore(moduleScore);
            uploadScore.setDetailRequireId(moduleForm.getDetailRequireId());
            int result = uploadScoreMapper.insertIntoScore(uploadScore);
            if(result == 0){
                return false;
            }
        }
        return true;
    }



    public boolean setStuScoreCache(Map<String,Object> map){
        try {
            redisUtil.hmset(cacheName,map,24*3*3600);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean updateStuScoreCache(UploadScoreForm uploadScoreForm){
        UploadScoreVo scoreVo = new UploadScoreVo();
        BeanUtils.copyProperties(uploadScoreForm,scoreVo);

        try {
            redisUtil.hset(cacheName,uploadScoreForm.getStuName(),scoreVo,24*3*3600);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }








}
