package com.george.mapper;

import com.george.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Geroge Chan
 * @since 2024-05-25 11:44:40
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
