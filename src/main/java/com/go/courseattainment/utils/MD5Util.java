package com.go.courseattainment.utils;

import com.go.courseattainment.entities.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sun.security.util.AuthResources_it;

/**
 * @program: miaosha
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-02-06 10:26
 **/
public class MD5Util {

    private static final String salt = "1a2b3c4d";

    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    public static String inputPassToFormPass(String inputPass){
        String str = "" + salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }

    public static String formPassToDBPass(String formpass, String salt){
        String str = "" + salt.charAt(0)+salt.charAt(2)+formpass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }

    /**
     将用户输入的密码转化为存储在数据库的密码
     */
    public static String inputPassToDbPass(String inputpass,String DBsalt){
        String formPass = inputPassToFormPass(inputpass);
        String dbPass = formPassToDBPass(formPass,DBsalt);
        return dbPass;
    }


    public static void main(String[] args) {
        User user = new User();
        user.setMajorId(0);
        user.setMajorId(1);
        user.setMajorId(2);
        System.out.println(user.toString());
    }





}
