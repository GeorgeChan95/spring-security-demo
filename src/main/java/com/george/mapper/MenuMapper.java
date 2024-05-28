package com.george.mapper;

import com.george.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author Geroge Chan
 * @since 2024-05-28 09:57:49
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 根据用户ID查询用户具有的权限集合
     * @param userId
     * @return
     */
    List<String> selectPermsByUserId(@Param("userId") Long userId);
}
