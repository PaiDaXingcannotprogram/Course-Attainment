package com.go.courseattainment.exception;


import com.go.courseattainment.enums.RespBeanEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: courseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-02-20 18:06
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalException extends RuntimeException {

    private RespBeanEnum respBeanEnum;

}
