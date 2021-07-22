package com.go.courseattainment.mapper;

import com.go.courseattainment.entities.CourseTarget;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-02 15:27
 **/
@Mapper
@Repository
public interface CourseTargetMapper {


    /**
     * 增加课程目标
     * @param courseTarget
     * @return int
     */
    @Insert("INSERT INTO `course_target` (\n" +
            "  `course_target_id`,\n" +
            "  `course_target`,\n" +
            "  `detail_desc`,\n" +
            "  `detail_require_id`,\n" +
            "  `display_order`\n" +
            ") \n" +
            "VALUES\n" +
            "  (#{courseTargetId},#{courseTarget},#{detailDesc},#{detailRequireId},#{displayOrder}); ")
    int insertIntoCourseTarget(CourseTarget courseTarget);




    /**
     * 删除课程目标
     * @param courseTargetId
     * @return int
     */
    @Delete("DELETE \n" +
            "FROM\n" +
            "  `course_target` \n" +
            "WHERE `course_target_id` = #{courseTargetId}; ")
    int deleteFromCourseTarget(Integer courseTargetId);



    /**
     * 编辑课程目标
     * @param courseTarget
     * @return int
     *
     *
     */
    @Update("UPDATE \n" +
            "  `course_target` \n" +
            "SET\n" +
            "  `course_target` = #{courseTarget},`detail_desc` = #{detailDesc},`detail_require_id` = #{detailRequireId},`display_order` = #{displayOrder} \n" +
            "WHERE `course_target_id` = #{courseTargetId}; ")
    int updateSetCourseTarget(CourseTarget courseTarget);



    /**
     * 查询所有课程目标
     * @return list
     */
    @Select("SELECT \n" +
            "  `course_target_id`,\n" +
            "  `course_target`,\n" +
            "  `detail_desc`,\n" +
            "  `detail_require_id`,\n" +
            "  `display_order` \n" +
            "FROM\n" +
            "  `course_target` \n" +
            "ORDER BY `display_order` ;\n")
    List<CourseTarget> selectAllCourseTargetsByOrder();
}
