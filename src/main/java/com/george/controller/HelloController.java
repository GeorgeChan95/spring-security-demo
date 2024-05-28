package com.george.controller;

import com.george.domain.ResponseResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p>
 *
 * @author George
 * @date 2024.05.25 09:36
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
//    @PreAuthorize("hasAuthority('system:dept:list')") // 登录用户必须包含 'test' 权限才能访问该接口
    //自定义权限校验的方法，huanfHasAuthority
    @PreAuthorize("@customEX.customHasAuthority('system:dept:list')")
    public String hello() {
        return "Hello Spring Security";
    }

    //

    /**
     * 基于配置的权限控制
     * 在 SecurityConfig 配置url需要的权限
     * @return
     */
    @RequestMapping("/configAuth")
    public ResponseResult configAuth(){
        return new ResponseResult(200,"访问成功");
    }
}
