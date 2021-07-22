package com.go.courseattainment.service.impl;

import com.go.courseattainment.entities.Module;
import com.go.courseattainment.enums.RespBeanEnum;
import com.go.courseattainment.form.ModuleForm;
import com.go.courseattainment.form.UpdateModuleForm;
import com.go.courseattainment.mapper.ModuleMapper;
import com.go.courseattainment.service.ModuleService;
import com.go.courseattainment.vo.ModuleVo;
import com.go.courseattainment.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-20 18:29
 **/
@Service
@Slf4j
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleMapper moduleMapper;


    @Override
    public RespBean getModuleByPrimaryKey(Integer moduleId) {
        Module module = moduleMapper.selectByPrimaryKey(moduleId);
        if(module!=null){
            return RespBean.SUCCESS(module);
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }

    @Override
    public RespBean addModule(ModuleForm moduleForm) {
        Module module = new Module();
        BeanUtils.copyProperties(moduleForm,module);
        int result = moduleMapper.insertIntoModule(module);
        if(result != 0){
            return RespBean.SUCCESS("添加成功");
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }

    @Override
    public RespBean deleteModule(Integer moduleId) {
        int result = moduleMapper.deleteModuleByPrimaryKey(moduleId);
        if(result != 0){
            return RespBean.SUCCESS("删除成功");
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }

    @Override
    public RespBean updateModule(UpdateModuleForm updateModuleForm) {
        Module module = new Module();
        BeanUtils.copyProperties(updateModuleForm,module);
        int result = moduleMapper.updateSetModule(module);
        if(result != 0){
            return RespBean.SUCCESS("更新成功");
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }


    @Override
    public RespBean getAllModules() {
        List<ModuleVo> moduleVoList = new ArrayList<>();
        List<Module> modules = moduleMapper.selectAllModules();
        for (Module module : modules){
            ModuleVo vo = new ModuleVo();
            BeanUtils.copyProperties(module,vo);
            moduleVoList.add(vo);
        }
        if(!moduleVoList.isEmpty()){
            return RespBean.SUCCESS(moduleVoList);
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }



    @Override
    public RespBean getAllCourseModules(Integer planId) {
        List<ModuleVo> moduleVoList = new ArrayList<>();
        List<Module> modules = moduleMapper.selectAllModules();
        for(Module module : modules){
            if(module.getPlanId().equals(planId)){
                ModuleVo vo = new ModuleVo();
                BeanUtils.copyProperties(module,vo);
                moduleVoList.add(vo);
            }
        }
        if(!moduleVoList.isEmpty()){
            return RespBean.SUCCESS(moduleVoList);
        }
        return RespBean.ERROR(RespBeanEnum.ERROR);
    }




}
