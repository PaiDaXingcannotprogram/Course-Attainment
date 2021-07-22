package com.go.courseattainment.entities;

import lombok.Data;

/**
 * @program: CourseAttainment
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-03-29 19:57
 **/
@Data
public class PhotoUpload {

    /**
     * 文件原始名
     */
    private String fileName;
    /**
     * 后缀 类似.ppt
     */
    private String suffix;
    /**
     * 新名称 以19700101开始的毫秒
     */
    private String newFileName;

    private String photoUrl;
}
