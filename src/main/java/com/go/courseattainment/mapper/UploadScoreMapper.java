package com.go.courseattainment.mapper;

import com.go.courseattainment.entities.Module;
import com.go.courseattainment.entities.UploadScore;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-24 22:02
 **/
@Mapper
@Repository
public interface UploadScoreMapper {


    /**
     * 上传模块成绩
     * @param uploadScore
     * @return int
     */
    @Insert("INSERT INTO `upload_score` (\n" +
            "  `upload_score_id`,\n" +
            "  `module_id`,\n" +
            "  `stu_no`,\n" +
            "  `stu_name`,\n" +
            "  `module_score`,\n" +
            "  `stu_status`\n" +
            "  `detail_require_id`\n" +
            ") \n" +
            "VALUES\n" +
            "  (\n" +
            "    #{uploadScoreId},\n" +
            "    #{moduleId},\n" +
            "    #{stuNo},\n" +
            "    #{stuName},\n" +
            "    #{moduleScore},\n" +
            "    #{stuStatus}\n" +
            "    #{detailRequireId}\n" +
            "  ) ;\n" +
            "\n")
    int insertIntoScore(UploadScore uploadScore);


    /**
     * 删除当年成绩
     * @param uploadScoreId
     * @return int
     */
    @Delete("DELETE \n" +
            "FROM\n" +
            "  `upload_score` \n" +
            "WHERE `upload_score_id` = #{uploadScoreId}; ")
    int deleteFromScore(Integer uploadScoreId);


    /**
     * 更新或者编辑学生模块分数
     * @param uploadScore
     * @return int
     */
    @Update("UPDATE \n" +
            "  `upload_score` \n" +
            "SET\n" +
            "  `module_id` = #{moduleId},`stu_no` = #{stuNo},`stu_name` = #{stuName},\n" +
            "  `module_score` = #{moduleScore},`stu_status` = #{stuStatus},`detail_require_id` = #{detailRequireId} \n" +
            "WHERE `upload_score_id` = #{uploadScoreId}; ")
    int updateSetScore(UploadScore uploadScore);


    /**
     * 获取所有学生模块分数
     * @return list
     */
    @Select("SELECT \n" +
            "  `upload_score_id`,\n" +
            "  `module_id`,\n" +
            "  `stu_no`,\n" +
            "  `stu_name`,\n" +
            "  `module_score`,\n" +
            "  `stu_status` \n" +
            "  `detail_require_id` \n" +
            "FROM\n" +
            "  `upload_score` ;\n" +
            "\n")
    List<UploadScore> selectAllScores();


    /**
     * 根据主键查找学生模块分数
     * @param uploadScoreId
     * @return  UploadScore
     */
    @Select("SELECT \n" +
            "  `upload_score_id`,\n" +
            "  `module_id`,\n" +
            "  `stu_no`,\n" +
            "  `stu_name`,\n" +
            "  `module_score`,\n" +
            "  `stu_status` \n" +
            "  `detail_require_id` \n" +
            "FROM\n" +
            "  `upload_score` \n" +
            "WHERE `upload_score_id` = #{uploadScoreId};")
    UploadScore selectByPrimaryKey(Integer uploadScoreId);




    /**
     * 根据moduleId获取
     * @param moduleId
     * @return list
     */
    @Select("SELECT \n" +
            "  `upload_score_id`,\n" +
            "  `module_id`,\n" +
            "  `stu_no`,\n" +
            "  `stu_name`,\n" +
            "  `module_score`,\n" +
            "  `stu_status`,\n" +
            "  `detail_require_id` \n" +
            "FROM\n" +
            "  `upload_score` \n" +
            "WHERE `module_id` = #{moduleId};")
    List<UploadScore> selectByModuleId(@Param("moduleId") Integer moduleId);
}
