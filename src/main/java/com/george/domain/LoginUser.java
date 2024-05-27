package com.george.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.george.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *     实现UserDetails接口之后，要重写UserDetails接口里面的7个方法
 * </p>
 *
 * @author George
 * @date 2024.05.25 15:21
 */
@Data //get和set方法
@AllArgsConstructor  //带参构造
@JsonIgnoreProperties(ignoreUnknown = true) // 解决实体字段不存在时，jackson反序列化异常的问题，参考：https://www.cnblogs.com/east7/p/15388789.html
public class LoginUser implements UserDetails {

    /**
     * 用户的基本信息：用户名、密码等
     */
    private User user;

    /**
     * 查询用户权限信息
     */
    private List<String> permissions;

    public LoginUser() {
    }

    public LoginUser(User user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    //我们把这个List写到外面这里了，注意成员变量名一定要是authorities，不然会出现奇奇怪怪的问题
    @JsonIgnore(value = true) // 这个注解的作用是不让下面那行的成员变量序列化存入redis，避免redis不支持而报异常
    private List<SimpleGrantedAuthority> authorities;

    /**
     * 用于返回权限信息
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        /* 方式一 */
        // 将权限信息封装成SimpleGrantedAuthority
//        authorities = new ArrayList<>();
//        for (String permission : permissions) {
//            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
//            authorities.add(authority);
//        }

        /* 方式二，使用lamda表达式 */
        //当authorities集合为空，就说明是第一次，就需要转换，当不为空就说明不是第一次，就不需要转换直接返回
        if(authorities != null){ //严谨来说这个if判断是避免整个调用链中security本地线程变量在获取用户时的重复解析，和redis存取无关
            return authorities;
        }
        authorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
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
