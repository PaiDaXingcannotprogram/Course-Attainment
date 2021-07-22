package com.go.courseattainment.mapper;

import com.go.courseattainment.entities.ResultsDisplay;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-07-08 19:29
 **/
@Mapper
@Repository
public interface ResultsDisplayMapper {

    /**
     * 根据主键获取学生最终结果
     * @param resultsDisplayId
     * @return  ResultsDisplay
     */
    @Select("SELECT \n" +
            "  `results_display_id`,\n" +
            "  `stu_id`,\n" +
            "  `stu_no`,\n" +
            "  `stu_name`,\n" +
            "  `detail_require_id`,\n" +
            "  `course_name`,\n" +
            "  `stu_achievement` \n" +
            "  `correlation` \n" +
            "FROM\n" +
            "  `results_display` \n" +
            "WHERE `results_display_id` = #{resultsDisplayId}; ")
     ResultsDisplay selectByPrimaryKey(@Param("resultsDisplayId") Integer resultsDisplayId);


    /**
     * 添加学生最终结果
     * @param resultsDisplay
     * @return int
     */
    @Insert("INSERT INTO `results_display` (\n" +
            "  `results_display_id`,\n" +
            "  `stu_id`,\n" +
            "  `stu_no`,\n" +
            "  `stu_name`,\n" +
            "  `detail_require_id`,\n" +
            "  `course_name`,\n" +
            "  `stu_achievement`\n" +
            "  `correlation`\n" +
            ") \n" +
            "VALUES\n" +
            "  (\n" +
            "    #{resultsDisplayId},#{stuId},#{stuNo},#{stuName},#{detailRequireId},#{courseName},\n" +
            "    #{stuAchievement},#{correlation}\n" +
            "  ) ;\n" +
            "\n")
    int insertIntoDisplay(ResultsDisplay resultsDisplay);


    /**
     * 删除学生最终结果
     * @param resultsDisplayId
     * @return int
     */
    @Delete("DELETE \n" +
            "FROM\n" +
            "  `results_display` \n" +
            "WHERE `results_display_id` = #{resultsDisplayId}; ")
    int deleteDisplay(@Param("resultsDisplayId") Integer resultsDisplayId);


    /**
     * 更新、编辑学生最终结果
     * @param resultsDisplay
     * @return int
     */
    @Update("UPDATE \n" +
            "SET\n" +
            "  `stu_id` = #{stuId},`stu_no` = #{stuNo},`stu_name` = #{stuName},\n" +
            "  `detail_require_id` = #{detailRequireId},\n" +
            "  `course_name` = #{courseName},`stu_achievement` = #{stuAchievement},`correlation` = #{correlation}\n" +
            " WHERE `results_display_id` = #{resultsDisplayId}; ")
    int updateSetDisplay(ResultsDisplay resultsDisplay);


    /**
     * 获取学生所有的最终结果
     * @return  list
     */
    @Select("SELECT \n" +
            "  `results_display_id`,\n" +
            "  `stu_id`,\n" +
            "  `stu_no`,\n" +
            "  `stu_name`,\n" +
            "  `detail_require_id`,\n" +
            "  `course_name`,\n" +
            "  `stu_achievement` \n" +
            "  `correlation` \n" +
            "FROM\n" +
            "  `results_display` ;\n")
    List<ResultsDisplay> selectAllDisplays();
}
