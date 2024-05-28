package com.george.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *     登录成功的处理器
 *     官方提供的AuthenticationSuccessHandler接口的实现类
 * </p>
 *
 * @author George
 * @date 2024.05.28 13:47
 */
@Component
public class MySuccessHandler implements AuthenticationSuccessHandler {

    /**
     * 实现security官方提供的AuthenticationSuccessHandler接口的下面这个抽象方法
     * @param request
     * @param response
     * @param authentication 认证之后的对象
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //我们只验证认证成功之后被调用到下面那行代码就行，如果是要自定义'登录成功的处理器'，那么就在下面写具体代码即可
        System.out.println("登录认证成功了^V^");
    }
}
