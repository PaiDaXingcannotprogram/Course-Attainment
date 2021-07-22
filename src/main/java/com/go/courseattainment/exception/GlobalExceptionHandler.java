package com.go.courseattainment.exception;

import com.go.courseattainment.vo.RespBean;
import com.go.courseattainment.enums.RespBeanEnum;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @program: sight
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-02-20 18:06
 **/

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public RespBean ExceptionHandler(Exception e){
        e.printStackTrace();
       if(e instanceof GlobalException){
           GlobalException exception = (GlobalException) e;
           return RespBean.ERROR(exception.getRespBeanEnum());
       }else if(e instanceof BindException){
           BindException e1 = (BindException) e;
           RespBean respBean = RespBean.ERROR(RespBeanEnum.BIND_ERROR);
           respBean.setMsg("参数校验异常："+ e1.getBindingResult().getAllErrors().get(0).getDefaultMessage());
           return respBean;
       }
       return RespBean.ERROR(RespBeanEnum.ERROR);
    }
}
