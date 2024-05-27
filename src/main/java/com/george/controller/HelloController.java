package com.george.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
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
    @PreAuthorize("hasAuthority('test')") // 登录用户必须包含 'test' 权限才能访问该接口
    public String hello() {
        return "Hello Spring Security";
    }
}
