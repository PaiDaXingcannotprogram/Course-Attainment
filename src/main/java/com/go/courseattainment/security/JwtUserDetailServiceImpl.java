package com.go.courseattainment.security;

import com.go.courseattainment.entities.User;
import com.go.courseattainment.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @program: CourseAttainment
 * @description 自定义UserDetailService类
 * @author: 不会编程的派大星
 * @create: 2021-03-26 12:38
 **/
@Service
@Slf4j
@Repository
public class JwtUserDetailServiceImpl  implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getUserByUsername(username);
        if(user == null){
            log.info("用户不存在");
            throw  new UsernameNotFoundException(String.format("用户名为 %s 的用户不存在",username));
        }
        Integer role = user.getRole();
        return new JwtUser(username,user.getPassword(),role);
    }
}
