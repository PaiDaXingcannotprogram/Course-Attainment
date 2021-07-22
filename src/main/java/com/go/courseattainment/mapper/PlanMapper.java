package com.go.courseattainment.mapper;

import com.go.courseattainment.entities.Plan;
import com.go.courseattainment.form.UpdatePlanForm;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-04-16 19:47
 **/
@Mapper
@Repository
public interface PlanMapper {

    /**
     * 增加教学计划
     * @param plan
     * @return int
     */
    @Insert("INSERT INTO `plan` (\n" +
            "  `plan_id`,\n" +
            "  `course_name`,\n" +
            "  `term`,\n" +
            "  `course_teacher`,\n" +
            "  `grade_id`,\n" +
            "  `major_id`,\n" +
            "  `course_status`,\n" +
            "  `computeTime`\n" +
            ") \n" +
            "VALUES\n" +
            "  (#{planId},#{courseName},#{term},#{courseTeacher},#{gradeId},#{majorId},#{courseStatus},#{computeTime}); ")
    int insertIntoPlaN(Plan plan);



    /**
     * 根据planId删除教学计划
     * @param planId
     * @return int
     */
    @Delete("DELETE \n" +
            "FROM\n" +
            "  `plan` \n" +
            "WHERE `plan_id` = #{planId}; ")
    int deleteByPlanId(@Param("planId") Integer planId);


    /**
     * 根据planId编辑教学计划
     * @param plan
     * @return int
     */
    @Update("UPDATE \n" +
            "  `plan` \n" +
            "SET\n" +
            "  `course_name` = #{courseName},`term` = #{term},`course_teacher` = #{courseTeacher}," +
            "`grade_id` = #{gradeId},`major_id` = #{majorId}," +
            "`course_status` = #{courseStatus},`compute_time` = #{computeTime} \n" +
            "WHERE `plan_id` = #{planId};  ")
    int updatePlan(Plan plan);


    /**
     * 根据主键查找教学计划
     * @param planId
     * @return plan
     */
    @Select("SELECT \n" +
            "  `plan_id`,\n" +
            "  `course_name`,\n" +
            "  `term`,\n" +
            "  `course_teacher`,\n" +
            "  `grade_id`,\n" +
            "  `major_id`,\n" +
            "  `course_status`,\n" +
            "  `compute_time` \n" +
            "FROM\n" +
            "  `plan` \n" +
            "WHERE `plan_id` = #{planId}; ")
    Plan selectByPrimaryKey(@Param("planId") Integer planId);




    /**
     * 获取所有教学加护
     * @return list
     */
    @Select("SELECT \n" +
            "  `plan_id`,\n" +
            "  `course_name`,\n" +
            "  `term`,\n" +
            "  `course_teacher`,\n" +
            "  `grade_id`,\n" +
            "  `major_id`,\n" +
            "  `course_status`,\n" +
            "  `compute_time` \n" +
            "FROM\n" +
            "  `plan` ")
    List<Plan> selectAllPlans();



}
