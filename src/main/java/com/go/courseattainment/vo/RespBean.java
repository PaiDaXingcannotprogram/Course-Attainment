package com.go.courseattainment.vo;

import com.go.courseattainment.enums.RespBeanEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-03-11 19:28
 **/
@AllArgsConstructor
@Data
@ApiModel("返回结果封装类")
public class RespBean<T> {

    @ApiModelProperty(value = "信息编码")
    private Integer code;

    @ApiModelProperty(value = "信息内容")
    private String msg;

    @ApiModelProperty(value = "返回的数据")
    private T data;


    /**
     * 成功时返回1.有data 2.无data
     * @return RespBean
     */
   public static RespBean SUCCESS(){
       return new RespBean(RespBeanEnum.SUCCESS.getCode(),RespBeanEnum.SUCCESS.getMsg(),null);
   }

    public static <T> RespBean SUCCESS(T data){
        return new RespBean(RespBeanEnum.SUCCESS.getCode(),RespBeanEnum.SUCCESS.getMsg(),data);
    }

    /**
     * 失败时返回，不同的错误返回不同的code和msg 1.有data 2.无data
     * @param respBeanEnum
     * @return RespBean
     */
    public static RespBean ERROR(RespBeanEnum respBeanEnum){
       return new RespBean(respBeanEnum.getCode(),respBeanEnum.getMsg(),null);
    }

    public static <T> RespBean ERROR(RespBeanEnum respBeanEnum,T data){
        return new RespBean(respBeanEnum.getCode(),respBeanEnum.getMsg(),data);
    }
}
