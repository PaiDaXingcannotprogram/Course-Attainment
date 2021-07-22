package com.go.courseattainment.mapper;

import com.go.courseattainment.entities.DetailRequire;
import com.go.courseattainment.entities.GradeRequire;
import com.go.courseattainment.form.GradeRequireForm;
import com.go.courseattainment.form.SplitDetailRequireForm;
import com.go.courseattainment.form.UpdateDetailRequireForm;
import com.go.courseattainment.form.UpdateGradeRequireForm;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-03-25 14:51
 **/
@Mapper
@Repository
public interface RequireMapper {


    /**
     * 增加一级指标点
     * @param gradeRequire
     * @return int
     */
    @Insert("INSERT INTO `grade_require` (\n" +
            "  `grade_require_id`,\n" +
            "  `detail_desc`,\n" +
            "  `brief_desc`,\n" +
            "  `grade_require_no`,\n" +
            "  `majorId`\n" +
            ") \n" +
            "VALUES\n" +
            "  (#{gradeRequireId},#{detailDesc},#{briefDesc},#{gradeRequireNo},#{majorId});   ")
    int insertIntoGradeRequire(GradeRequire gradeRequire);


    /**
     * 编辑一级指标点
     * @param gradeRequire
     * @return int
     */
    @Update("UPDATE \n" +
            "  `grade_require` \n" +
            "SET\n" +
            "  `detail_desc` = #{detailDesc},`brief_desc` = #{briefDesc},`majorId` = #{majorId} \n" +
            "WHERE `grade_require_no` = #{gradeRequireNo}; ")
    int updateSetGradeRequire(GradeRequire gradeRequire);


    /**
     * 根据一级指标点序号拆分一级指标点
     * @param gradeRequireNo
     * @return list
     */
    @Select("SELECT \n" +
            "  `detail_require_id`,\n" +
            "  `detail_require_no`,\n" +
            "  `grade_require_no`,\n" +
            "  `detail_desc`,\n" +
            "  `major_id` \n" +
            "FROM\n" +
            "  `detail_require` \n" +
            "WHERE `grade_require_no` = #{gradeRequireNo}; ")
    List<DetailRequire> selectByGradeRequireNo(Integer gradeRequireNo);



    /**
     * 添加二级指标点
     * @param detailRequire
     * @return int
     */
    @Insert("INSERT INTO `detail_require` (\n" +
            "  `detail_require_id`,\n" +
            "  `detail_require_no`,\n" +
            "  `grade_require_no`,\n" +
            "  `detail_desc`,\n" +
            "  `major_id`\n" +
            ") \n" +
            "VALUES\n" +
            "  (#{detailRequireId},#{detailRequireNo},#{gradeRequireNo},#{detailDesc},#{majorId}); ")
    int insertIntoDetailRequire(DetailRequire detailRequire);


    /**
     * 修改二级指标点
     * @param detailRequire
     * @return int
     */
    @Update("UPDATE \n" +
            "  `detail_require` \n" +
            "SET\n" +
            "  `detail_require_no` = #{detailRequireNo},\n" +
            "  `grade_require_no` = #{gradeRequireNo},\n" +
            "  `detail_desc` = #{detailDesc},\n" +
            "  `major_id` = #{majorId} \n" +
            "WHERE `detail_require_id` = #{detailRequireId}; ")
    int updateSetDetailRequire(DetailRequire detailRequire);


    /**
     * 根据
     * @param detailRequireId
     * @return int
     */
    @Delete("DELETE \n" +
            "FROM\n" +
            "  `detail_require` \n" +
            "WHERE `detail_require_id` = #{detailRequireId}; ")
    int deleteByPrimaryKey(Integer detailRequireId);




    /**
     * 根据no查找gradeRequire
     * @param gradeRequireNo
     * @return GradeRequire
     */
    @Select("SELECT \n" +
            "  `grade_require_id`,\n" +
            "  `detail_desc`,\n" +
            "  `brief_desc`,\n" +
            "  `grade_require_no`,\n" +
            "  `majorId` \n" +
            "FROM\n" +
            "  `grade_require` \n" +
            "WHERE `grade_require_no` = #{gradeRequireNo}; ")
    GradeRequire selectOneByGradeRequireNo(Integer gradeRequireNo);


    /**
     * 根据主键 detailRequireId查找单个detailRequires
     * @param detailRequireId
     * @return  DetailRequire
     */
    @Select("SELECT \n" +
            "  `detail_require_id`,\n" +
            "  `detail_require_no`,\n" +
            "  `grade_require_no`,\n" +
            "  `detail_desc`,\n" +
            "  `major_id` \n" +
            "FROM\n" +
            "  `detail_require` \n" +
            "WHERE `detail_require_id` = #{detailRequireId};")
    DetailRequire selectOneByDetailRequireId(Integer detailRequireId);


    /**
     * 获取所有一级指标点
     * @return list
     */
    @Select("SELECT \n" +
            "  `grade_require_id`,\n" +
            "  `detail_desc`,\n" +
            "  `brief_desc`,\n" +
            "  `grade_require_no`,\n" +
            "  `majorId` \n" +
            "FROM\n" +
            "  `grade_require` \n" +
            "WHERE `majorId` = #{majorId} \n" +
            "ORDER BY `grade_require_no`; ")
    List<GradeRequire> selectAllGradeRequires(@Param("majorId") Integer majorId);


    /**
     * 获取所有二级指标点
     * @param majorId
     * @return list
     */
    @Select("SELECT \n" +
            "  `detail_require_id`,\n" +
            "  `detail_require_no`,\n" +
            "  `grade_require_no`,\n" +
            "  `detail_desc`,\n" +
            "  `major_id` \n" +
            "  FROM `detail_require`\n" +
            "WHERE `major_id` = #{majorId} ;")
    List<DetailRequire> selectAllDetailRequires(@Param("majorId") Integer majorId);







}
