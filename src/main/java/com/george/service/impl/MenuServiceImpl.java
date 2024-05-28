package com.george.service.impl;

import com.george.entity.Menu;
import com.george.mapper.MenuMapper;
import com.george.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author Geroge Chan
 * @since 2024-05-28 09:57:49
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}
