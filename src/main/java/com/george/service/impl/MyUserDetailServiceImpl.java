package com.george.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.george.domain.LoginUser;
import com.george.entity.User;
import com.george.mapper.MenuMapper;
import com.george.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *     实现Security官方的UserDetailsService接口，然后重写里面的loadUserByUsername方法
 * </p>
 *
 * @author George
 * @date 2024.05.25 15:25
 */
@Service
public class MyUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;


    /**
     * UserDetails是Security官方提供的接口
     * @param username 用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectOne(Wrappers.<User>query().lambda().eq(User::getUserName, username).last(" limit 1"));
        //如果用户传进来的用户名，但是数据库没有这个用户名，就会导致我们是查不到的情况，那么就进行下面的判断。避免程序安全问题
        if(Objects.isNull(user)){//判断user对象是否为空。当在数据库没有查到数据时，user就会为空，也就会进入这个判断
            throw new RuntimeException("用户名或者密码错误");
        }

        // 自定义权限集合，等下还要在LoginUser类做权限集合的转换
        List<String> perms = menuMapper.selectPermsByUserId(user.getId());

        //把查询到的user结果，封装成UserDetails类型，然后返回。
        //但是由于UserDetails是个接口，所以我们先需要在domino目录新建LoginUser类，作为UserDetails的实现类，再写下面那行
        return new LoginUser(user, perms);
    }
}
