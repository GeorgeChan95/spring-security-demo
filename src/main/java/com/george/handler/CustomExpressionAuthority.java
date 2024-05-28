package com.george.handler;

import com.george.domain.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *     自定义权限校验的方法
 * </p>
 *
 * @author George
 * @date 2024.05.28 12:51
 */
@Component("customEX")
public class CustomExpressionAuthority {

    /**
     * 自定义权限校验的方法
     * @param authority
     * @return
     */
    public boolean customHasAuthority(String authority) {
        // 获取用户具有的权限字符串，有可能用户具有多个权限字符串，所以获取后是一个集合
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        List<String> permissions = loginUser.getPermissions();

        // 判断用户权限集合中，是否存在跟业务接口(业务接口的权限字符串会作为authority形参传进来)一样的权限
        return permissions.contains(authority);
    }
}
