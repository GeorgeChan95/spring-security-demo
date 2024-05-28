package com.george.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.george.domain.ResponseResult;
import com.george.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *     只处理授权异常，不处理认证异常
 * </p>
 *
 * @author George
 * @date 2024.05.28 10:40
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Autowired
    private ObjectMapper objectMapper;

    /**
     *
     * @param request 请求对象
     * @param response 响应对象
     * @param accessDeniedException 异常对象。把异常封装成认证的对象，然后封装到handle方法
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // HttpStatus是spring提供的枚举类，FORBIDDEN表示403状态码
        ResponseResult result = new ResponseResult(HttpStatus.FORBIDDEN.value(), "您没有权限进行访问");
        //把上面那行拿到的result对象转换为JSON字符串
        String json = objectMapper.writeValueAsString(result);
        //WebUtils是我们在utils目录写好的类
        WebUtils.renderString(response,json);
    }
}
