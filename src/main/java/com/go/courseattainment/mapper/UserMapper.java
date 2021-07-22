package com.go.courseattainment.mapper;

import com.go.courseattainment.entities.User;
import com.go.courseattainment.vo.updatePwVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: CourseAttainment
 * @description 用户控制类
 * @author: 不会编程的派大星
 * @create: 2021-03-11 19:47
 **/
@Mapper
@Repository
@Component
public interface UserMapper {

    /**
     * 根据username查找user信息
     * @param username
     * @return user
     */
    @Select("SELECT \n" +
            "  `user_id`,\n" +
            "  `username`,\n" +
            "  `password`,\n" +
            "  `role`,\n" +
            "  `photo`,\n" +
            "  `major_id`,\n" +
            "  `name` \n" +
            "FROM\n" +
            "  `user` \n" +
            "WHERE `username` = #{username}; ")
    User getUserByUsername(@Param("username") String username);


    /**
     * 根据用户主键查询用户
     * @param userId
     * @return User
     */
    @Select("SELECT \n" +
            "  * \n" +
            "FROM\n" +
            "  `user` \n" +
            "WHERE `user_id` = #{userId} ;\n" +
            "\n")
    User selectByPrimaryKey(@Param("userId") Integer userId);

    /**
     * 根据角色查找用户
     * @param role
     * @return List
     */
    @Select(" select *\n" +
            "    from user\n" +
            "    where `role` = #{role}")
    List<User> selectByRole(@Param("role") String role);



    /**
     * 查询所有用户
     * @return List
     */
    @Select("SELECT \n" +
            "  * \n" +
            "FROM\n" +
            "  USER ;\n" +
            "\n")
    public List<User> selectALl();


    /**
     * 根据用户主键删除用户
     * @param userId
     * @return int
     */
    @Delete("DELETE \n" +
            "FROM\n" +
            "  `user` \n" +
            "WHERE `user_id` = #{userId} ;\n" +
            "\n")
    int deleteByPrimaryKey(@Param("userId") Integer userId);


    /**
     * 根据用户主键修改表用户信息
     * * @param user
     * @return int
     */
    @Update("update user\n" +
            "    set username = #{username},\n" +
            "      `name` = #{name},\n" +
            "      `password` = #{password},\n" +
            "      `role` = #{role},\n" +
            "      photo = #{photo},\n" +
            "      major_id = #{majorId}\n" +
            "    where user_id = #{userId}")
    int updateByPrimaryKey(User user);

    /**
     * 根据用户名修改用户信息
     * @param updatePwVo
     * @return
     */
    @Update("update \n" +
            "  `user` \n" +
            "set\n" +
            "  `password` = #{password} \n" +
            "where username = #{username} ;\n")
    int updateByUsername(updatePwVo updatePwVo);

    /**
     * 添加用户
     * @param user
     * @return int
     */
    @Insert("insert into user (username, `name`, \n" +
            "      `password`, `role`, `photo`, \n" +
            "      `major_id`)\n" +
            "    values ( #{username}, #{name}, \n" +
            "      #{password}, #{role}, #{photo}, \n" +
            "      #{majorId})")
    int insertIntoUser(User user);


    /**
     * 查找所有老师的用户名
     * @return
     */
    @Select("SELECT \n" +
            "  `name` \n" +
            "FROM\n" +
            "  `user` \n" +
            "WHERE `role` = 0 ;")
    List<String> selectAllTeacherNames();

}
