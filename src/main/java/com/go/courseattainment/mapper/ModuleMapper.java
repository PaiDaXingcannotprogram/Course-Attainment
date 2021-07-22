package com.go.courseattainment.mapper;

import com.go.courseattainment.entities.Module;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-20 18:28
 **/
@Mapper
@Repository
public interface ModuleMapper {



    /**
     * 根据主键查找module
     * @param moduleId
     * @return module
     */
    @Select("SELECT \n" +
            "  `module_id`,\n" +
            "  `course_name`,\n" +
            "  `module_type`,\n" +
            "  `course_proportion`,\n" +
            "  `plan_id`,\n" +
            "FROM\n" +
            "  `module` \n" +
            "WHERE `module_id` = #{moduleId};")
    Module selectByPrimaryKey(Integer moduleId);


    /**
     * 添加module
     * @param module
     * @return
     */
    @Insert("INSERT INTO `module` (\n" +
            "  `module_id`,\n" +
            "  `course_name`,\n" +
            "  `module_type`,\n" +
            "  `course_proportion`\n" +
            "  `plan_id`\n" +
            ") \n" +
            "VALUES\n" +
            "  (#{moduleId},#{courseName},#{moduleType},#{courseProportion}); ")
    int insertIntoModule(Module module);


    /**
     * 根据主键删除module
     * @param moduleId
     * @return int
     */
    @Delete("DELETE \n" +
            "FROM\n" +
            "  `module` \n" +
            "WHERE `module_id` = #{moduleId}; ")
    int deleteModuleByPrimaryKey(Integer moduleId);


    /**
     * 更新、编辑module
     * @param module
     * @return int
     */
    @Update("UPDATE \n" +
            "  `module` \n" +
            "SET\n" +
            "  `course_name` = #{courseName},\n" +
            "  `module_type` = #{moduleType},\n" +
            "  `course_proportion` = #{courseProportion}\n" +
            "  `plan_id` = #{planId}\n" +
            "WHERE `module_id` = #{moduleId}; ")
    int updateSetModule(Module module);


    /**
     * 获取虽有module
     * @return list
     */
    @Select("SELECT \n" +
            "  `module_id`,\n" +
            "  `course_name`,\n" +
            "  `module_type`,\n" +
            "  `course_proportion` \n" +
            "  `plan_id` \n" +
            "FROM\n" +
            "  `module`;\n")
    List<Module> selectAllModules();

}
