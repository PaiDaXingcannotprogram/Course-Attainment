package com.go.courseattainment.mapper;

import com.go.courseattainment.entities.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: CourseAttainment
 * @description 学生模块mapper接口
 * @author: 不会编程的派大星
 * @create: 2021-04-15 10:51
 **/
@Mapper
@Repository
public interface StudentMapper {

    /**
     * 添加学生信息
     * @param student
     * @return int
     */
    @Insert("INSERT INTO `student` (\n" +
            "  `stu_id`,\n" +
            "  `stu_no`,\n" +
            "  `stu_name`,\n" +
            "  `phone_number`,\n" +
            "  `stu_email`,\n" +
            "  `major_id`,\n" +
            "  `grade_id`\n" +
            ") \n" +
            "VALUES\n" +
            "  (#{stuId},#{stuNo},#{stuName},#{phoneNumber},#{stuEmail},#{majorId},#{gradeId}); ")
    int insertIntoStu(Student student);


    /**
     * 编辑学生信息
     * @param student
     * @return int
     */
    @Update("UPDATE \n" +
            "  `student` \n" +
            "SET\n" +
            "  `stu_no` = #{stuNo},`stu_name` = #{stuName},`phone_number` = #{phoneNumber},`stu_email` = #{stuEmail}," +
            "`major_id` = #{majorId},`grade_id` = #{gradeId} where `stu_id` = #{stuId};  ")
    int updateStu(Student student);




    /**
     * 删除单个学生
     * @param stuId
     * @return int
     */
    @Delete("DELETE \n" +
            "FROM\n" +
            "  `student` \n" +
            "WHERE `stu_id` = #{stuId}; ")
    int deleteStuByStuId(Integer stuId);




    /**
     * 根据学号查找学生
     * @param stuNo
     * @return
     */
    @Select("SELECT \n" +
            "  `stu_id`,\n" +
            "  `stu_no`,\n" +
            "  `stu_name`,\n" +
            "  `phone_number`,\n" +
            "  `stu_email`,\n" +
            "  `major_id`,\n" +
            "  `grade_id` \n" +
            "FROM\n" +
            "  `student` \n" +
            "WHERE `stu_no` = #{stuNo}; ")
    Student selectByStuNo(String stuNo);


    /**
     * 根据姓名查找学生
     * @param stuName
     * @return
     */
    @Select("SELECT \n" +
            "  `stu_id`,\n" +
            "  `stu_no`,\n" +
            "  `stu_name`,\n" +
            "  `phone_number`,\n" +
            "  `stu_email`,\n" +
            "  `major_id`,\n" +
            "  `grade_id` \n" +
            "FROM\n" +
            "  `student` \n" +
            "WHERE stuName = #{stuName}; ")
    List<Student> selectByStuName(String stuName);




    /**
     *查看单个学生是否存在
     * @param stuNo
     * @return int
     */
    @Select("SELECT \n" +
            "  `stu_id` \n" +
            "FROM\n" +
            "  `student` \n" +
            "WHERE `stu_no` = #{stuNo};")
    int ifStuExist(String stuNo);


    /**
     * 查找当年专业和年纪的学生
     * @param majorId
     * @param gradeId
     * @return list
     */
    @Select("SELECT \n" +
            "  `stu_id`,\n" +
            "  `stu_no`,\n" +
            "  `stu_name`,\n" +
            "  `phone_number`,\n" +
            "  `stu_email`,\n" +
            "  `major_id`,\n" +
            "  `grade_id` \n" +
            "FROM\n" +
            "  `student` \n" +
            "WHERE `major_id` = #{majorId}\n" +
            "AND `grade_id` = #{gradeId}; ")
    List<Student> selectCurrentMajorAndGradeStus(Integer majorId,Integer gradeId);


    /**
     * 根据主键查找学生
     * @param stuId
     * @return Student
     */
    @Select("SELECT \n" +
            "  `stu_id`,\n" +
            "  `stu_no`,\n" +
            "  `stu_name`,\n" +
            "  `phone_number`,\n" +
            "  `stu_email`,\n" +
            "  `major_id`,\n" +
            "  `grade_id` \n" +
            "FROM\n" +
            "  `student` \n" +
            "WHERE `stu_id` = #{stuId};")
    Student selectByPrimaryKey(Integer stuId);


    /**
     * 根据学号查找学生姓名
     * @param stuNo
     * @return student
     */
    @Select("SELECT \n" +
            "  `stu_name` \n" +
            "FROM\n" +
            "  `student` \n" +
            "WHERE `stu_no` = #{stuNo}; ")
    String selectStuNameByNo(String stuNo);

















}
