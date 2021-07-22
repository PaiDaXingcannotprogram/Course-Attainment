package com.go.courseattainment.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-06-20 15:27
 **/
@Data
public class RelevancyForm {

    @ApiModelProperty("课程名称")
    @NotNull
    private String courseName;

    @ApiModelProperty("关联度键值对集合--键值对格式<二级指标点Id,关联度>")
    @NotNull
    private IdentityHashMap<String,BigDecimal> relevancyMap;
}
