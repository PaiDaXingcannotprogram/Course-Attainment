package com.go.courseattainment.mapper;

import com.go.courseattainment.entities.Module;
import com.go.courseattainment.entities.ModuleQuestion;
import com.go.courseattainment.form.CalculateModuleSupportedScoreForm;
import com.go.courseattainment.form.ModuleQuestionForm;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-05 20:35
 **/
@Mapper
@Repository
public interface ModuleQuestionMapper {

    /**
     * 添加模块小题
     * @param moduleQuestion
     * @return int
     */
    @Insert("INSERT INTO `module_question` (\n" +
            "  `module_question_id`,\n" +
            "  `module_id`,\n" +
            "  `question_no`,\n" +
            "  `question_score`,\n" +
            "  `detail_require_id`,\n" +
            "  `display_order`\n" +
            ") \n" +
            "VALUES\n" +
            "  (#{moduleQuestionId},#{moduleId},#{questionNo},#{questionScore},#{detailRequireId},#{displayOrder});  ")
    int insertIntoQuestion(ModuleQuestion moduleQuestion);

    /**
     * 删除模块小题
     * @param moduleQuestionId
     * @return int
     */
    @Delete("DELETE \n" +
            "FROM\n" +
            "  `module_question` \n" +
            "WHERE `module_question_id` = #{moduleQuestionId}; ")
    int deleteFromQuestion(Integer moduleQuestionId);


    /**
     * 更新、编辑模块箱小题
     * @param moduleQuestion
     * @return int
     */
    int updateQuestion(ModuleQuestion moduleQuestion);


    /**
     * 获取所有排序好的模块小题
     * @return list
     */
    @Select("SELECT \n" +
            "  `module_question_id`,\n" +
            "  `module_id`,\n" +
            "  `question_no`,\n" +
            "  `question_score`,\n" +
            "  `detail_require_id`,\n" +
            "  `display_order` \n" +
            "FROM\n" +
            "  `module_question` \n" +
            "ORDER BY `display_order` ;\n")
    List<ModuleQuestion> selectAllQuestionsByOrder();



    /**
     * 获取该模块二级指标点对应的总分
     * @param moduleId
     * @return list
     */
    @Select("SELECT \n" +
            "  SUM(`question_score`) AS detail_require_id_total_score,\n" +
            "  `detail_require_id` \n" +
            "FROM\n" +
            "  `module_question` \n" +
            "  WHERE `module_id` = #{moduleId} \n" +
            "GROUP BY `detail_require_id` ;")
    List<CalculateModuleSupportedScoreForm> selectModuleTotalScore(@Param("moduleId") Integer moduleId);


    /**
     * 根据题号找对应的耳指标点
     * @param questionNo
     * @return
     */
    @Select("SELECT \n" +
            "  `detail_require_id` \n" +
            "FROM\n" +
            "  `module_question` \n" +
            "WHERE `question_no` = #{questionNo};")
    String selectDetailIdByNo(Integer questionNo);

}
