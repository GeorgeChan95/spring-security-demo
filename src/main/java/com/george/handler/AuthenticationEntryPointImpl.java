package com.george.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.george.domain.ResponseResult;
import com.george.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *     处理认证异常，不处理授权异常
 * </p>
 *
 * @author George
 * @date 2024.05.28 10:34
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper objectMapper;

    /**
     *
     * @param request 请求对象
     * @param response 响应对象
     * @param authExceptione 异常对象。把异常封装成授权的对象，然后封装到handle方法
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authExceptione) throws IOException, ServletException {
        // HttpStatus是spring提供的枚举类，UNAUTHORIZED表示401状态码
        ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "用户认证失败，请重新登录");
        //把上面那行拿到的result对象转换为JSON字符串
        String json = objectMapper.writeValueAsString(result);
        //WebUtils是我们在utils目录写好的类
        WebUtils.renderString(response,json);
    }
}
