package com.george.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.george.domain.ResponseResult;
import com.george.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *     登出成功的处理器
 *     官方提供的LogoutSuccessHandler接口的实现类
 * </p>
 *
 * @author George
 * @date 2024.05.28 14:23
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("退出登录成功");
        // HttpStatus是spring提供的枚举类，FORBIDDEN表示403状态码
        ResponseResult result = new ResponseResult(HttpStatus.OK.value(), "退出登录成功");
        //把上面那行拿到的result对象转换为JSON字符串
        String json = objectMapper.writeValueAsString(result);
        WebUtils.renderString(response, json);
    }
}
