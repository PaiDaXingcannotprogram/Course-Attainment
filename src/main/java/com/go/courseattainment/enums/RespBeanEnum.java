package com.go.courseattainment.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-03-11 19:30
 **/
@Getter
@AllArgsConstructor
public enum  RespBeanEnum {

    /**
     * 通用：1.成功时返回 code : 200,
     * 2.失败时返回code:400
     */
    SUCCESS(200,"SUCCESS"),
    ERROR(400,"服务端异常"),
    BIND_ERROR(500,"参数参数错误"),

    /**
     * 登录发生异常时返回 1200 - 1299
     */
    CAPTCHA_IS_ERROR(1200,"验证码错误"),
    CODE_NOT_EXIST(1201,"验证码不存在,请重新获取"),
    USER_NOT_EXIST(1202,"用户不存在"),
    LOGIN_ERROR(1203,"密码不正确"),
    WITHOUT_AUTH(1204,"抱歉，你没有访问权限"),
    AUTHENTICATION_ERROR(1205, "用户认证失败,请重新登录"),
    PERMISSION_DENNY(1206, "权限不足"),
    /**
     * 注册时发生异常时返回,1300-1399
     */
    PARAMETER_ERROR(1300,"必填项未填"),
    USER_HAS_REPEAT(1301,"该用户已存在!"),
    REGISTER_ERROR(1302,"注册失败"),

    /**
     * 用户操作异常,1400-1499
     */
    IS_NOT_PERSONAL_OPERATION(1400,"非本人操作"),
    PASSWORD_ERROR(1401,"密码错误"),

    /**
     * 指标点异常处理,1500-1599
     */
    DETAIL_REQUIRE_NOT_EXIST(1501,"二级指标点不存在"),


    /**
     * Excel表解析时，发生的错误,1600-1699
     */
    FILE_BLANK(1600,"文件导入失败"),
    EXCEL_BLANK(1601,"Excel表格数据为空！"),

    /**
     * 学生信息错误处理,1700-1799
     */
    STU_NOT_EXIST(1700,"学生信息不存在"),
    SAVE_INFO_BLANK(1701,"新增信息未填写"),
    STU_IS_EXIST(1702,"学生信息已存在"),
    SELECT_INFO_BLANK(1703,"查询条件不能为空"),
    STUS_INFOS_REPEAT(1704,"这些学生信息已存在"),
    SOME_STU_ADD_ERROR(1705,"部分学生添加失败"),

    /**
     * 课程方案异常处理 1800-1899
     */
    SCHEME_NOT_EXIST(1800,"选课计划不存在"),
    SCHEME_REPEAT(1801,"课程重复"),
    UPLOAD_ERROR(1802,"上传失败"),
    RELEVANCY_ADD_ERROR(1803,"关联度添加失败"),




    UPLOAD_SCORE_ERROR(1900,"学生成绩上传失败"),

    /**
     * 教学计划异常处理
     */
    CURRENT_TEACHER_PLAN_EMPTY(2100,"当前老师的没有需要计算的教学计划"),


    ;



    private final Integer code;

    private final String msg;
}
