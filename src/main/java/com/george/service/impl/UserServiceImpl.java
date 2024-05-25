package com.george.service.impl;

import com.george.entity.User;
import com.george.mapper.UserMapper;
import com.george.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Geroge Chan
 * @since 2024-05-25 11:44:40
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
