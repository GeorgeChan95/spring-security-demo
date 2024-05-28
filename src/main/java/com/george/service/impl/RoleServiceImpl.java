package com.george.service.impl;

import com.george.entity.Role;
import com.george.mapper.RoleMapper;
import com.george.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Geroge Chan
 * @since 2024-05-28 09:57:49
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
