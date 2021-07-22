package com.go.courseattainment.mapper;

import com.go.courseattainment.entities.CourseScheme;
import com.go.courseattainment.vo.CourseSchemeVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-04-02 18:27
 **/
@Mapper
@Repository
public interface CourseSchemeMapper {


    /**
     * 添加选课表  选课方案
     * @param courseScheme
     * @return
     */
    @Insert("insert into `course_scheme` (\n" +
            "  `course_id`,\n" +
            "  `course_name`,\n" +
            "  `credit`,\n" +
            "  `course_en_name`,\n" +
            "  `major_id`\n" +
            ") \n" +
            "values\n" +
            "  (#{courseId}, #{courseName}, #{credit}, " +
            " #{courseEnName}, #{majorId}) ;\n")
    int insertIntoCourseScheme(CourseScheme courseScheme);


    /**
     * 根据课程号查找选课表
     * @param courseId
     * @return
     */
    @Select("SELECT \n" +
            "  * \n" +
            "FROM\n" +
            "  `course_scheme` \n" +
            "WHERE `course_id` = #{courseId} ;\n")
    CourseScheme selectBycourseId(@Param("courseId") String courseId);





    /**
     * 获取当前年级的矩阵课程方案
     * @param majorId
     * @return list
     */
    @Select("SELECT \n" +
            "  `id`,\n" +
            "  `course_id`,\n" +
            "  `course_name`,\n" +
            "  `credit`,\n" +
            "  `course_requirement`,\n" +
            "  `core_course`,\n" +
            "  `inter_course`,\n" +
            "  `course_en_name`,\n" +
            "  `major_id` \n" +
            "FROM\n" +
            "  `course_scheme` \n" +
            "WHERE `major_id` = #{majorId};")
    List<CourseScheme> selectByMajorId(@Param("majorId") Integer majorId);


}
