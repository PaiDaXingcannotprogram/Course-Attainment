package com.go.courseattainment.mapper;

import com.go.courseattainment.entities.Relevancy;
import com.go.courseattainment.vo.RelevancyVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-04-07 21:07
 **/
@Mapper
@Repository
public interface RelevancyMapper {


    /**
     * 添加关联度
     * @param relevancy
     * @return int
     */
    @Insert("INSERT INTO `relevancy` (\n" +
            "  `course_name`,\n" +
            "  `detail_require_id`,\n" +
            "  `correlation`\n" +
            "  `majorId`\n" +
            ") \n" +
            "VALUES\n" +
            "  (#{courseName}, #{detailRequireId}, #{correlation},#{majorId}) ;\n" +
            "\n")
    int insertRelevancy(Relevancy relevancy);


    /**
     * 获取关联度
     * @return list
     */
    @Select("SELECT \n" +
            "  `id`,\n" +
            "  `course_name`,\n" +
            "  `detail_require_id`,\n" +
            "  `correlation`,\n" +
            "  `major_id` \n" +
            "FROM\n" +
            "  `relevancy` ;\n")
    List<Relevancy> getAllRelevancy();
}
