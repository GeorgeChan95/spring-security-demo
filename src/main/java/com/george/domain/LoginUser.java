package com.george.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.george.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * <p>
 *     实现UserDetails接口之后，要重写UserDetails接口里面的7个方法
 * </p>
 *
 * @author George
 * @date 2024.05.25 15:21
 */
@Data //get和set方法
@NoArgsConstructor //无参构造
@AllArgsConstructor  //带参构造
@JsonIgnoreProperties(ignoreUnknown = true) // 解决实体字段不存在时，jackson反序列化异常的问题，参考：https://www.cnblogs.com/east7/p/15388789.html
public class LoginUser implements UserDetails {

    private User user;

    /**
     * 用于返回权限信息
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * 用于获取用户密码, 由于使用的实体类是User，所以获取的是数据库的用户密码
     * @return
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * 用于获取用户名, 由于使用的实体类是User，所以获取的是数据库的用户名
     * @return
     */
    @Override
    public String getUsername() {
        return user.getUserName();
    }

    /**
     * 判断登录状态是否过期。如果返回true，表示永不过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 判断账号是否被锁定,如果返回true，表示未锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 判断登录凭证是否过期。如果返回true，表示永不过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 判断用户是否可用。如果返回true，表示可用状态
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
