package com.george.config;

import com.george.filter.JwtAuthenticationTokenFilter;
import com.george.handler.MyFailureHandler;
import com.george.handler.MyLogoutSuccessHandler;
import com.george.handler.MySuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * <p>
 *     实现Security提供的WebSecurityConfigurerAdapter类，就可以改变密码校验的规则了
 * </p>
 *
 * @author George
 * @date 2024.05.25 16:00
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)// Spring Security 开启基于注解的权限控制访问方案，例如：@PreAuthorize
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 把BCryptPasswordEncoder对象注入Spring容器中，SpringSecurity就会使用该PasswordEncoder来进行密码校验
     * 注意也可以注入PasswordEncoder，效果是一样的，因为PasswordEncoder是BCry..的父类
     * @return
     */
    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private MySuccessHandler successHandler;

    @Autowired
    private MyFailureHandler failureHandler;

    @Autowired
    private MyLogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    // 注入自定义的认证失败的处理器，
    // AuthenticationEntryPointImpl 实现 AuthenticationEntryPoint，替换官方的实现
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    // 注入自定义的授权失败处理器，
    // AccessDeniedHandlerImpl 实现 AccessDeniedHandler，替换官方的实现
    private AccessDeniedHandler accessDeniedHandler;

    /**
     * 注入AuthenticationManager接口，用于用户登录时的认证
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 表单登录时，自定义登录成功和登录失败的处理器，在前后端分离的环境中用不到
//        http.formLogin()
//                //登录认证成功的处理器
//                .successHandler(successHandler)
//                //登录认证失败的处理器
//                .failureHandler(failureHandler);

        http
                //由于是前后端分离项目，所以要关闭csrf
                .csrf().disable()
                //由于是前后端分离项目，所以session是失效的，我们就不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //指定让spring security放行登录接口的规则
                .authorizeRequests()
                // 对于登录接口 anonymous表示允许匿名访问
                .antMatchers("/user/login").anonymous()
                //基于配置的的权限控制。指定接口的地址，为HelloController类里面的/configAuth接口，指定权限为system:dept:list
                .antMatchers("/configAuth").hasAuthority("system:dept:list")
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();

        //把token校验过滤器添加到过滤器链中
        //第一个参数是上面注入的我们在filter目录写好的类，第二个参数表示你想添加到哪个过滤器之前
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //---------------------------异常处理的相关配置-------------------------------
        http.exceptionHandling()
                //配置认证失败的处理器
                .authenticationEntryPoint(authenticationEntryPoint)
                //配置授权失败的处理器
                .accessDeniedHandler(accessDeniedHandler);

        http.logout().logoutSuccessHandler(logoutSuccessHandler);
    }
}
