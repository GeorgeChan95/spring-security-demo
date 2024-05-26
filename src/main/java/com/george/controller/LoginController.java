package com.george.controller;

import com.george.domain.ResponseResult;
import com.george.entity.User;
import com.george.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p>
 *
 * @author George
 * @date 2024.05.25 16:50
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 用户登录
     * @param user
     * @return
     */
    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        //登录
        return loginService.login(user);
    }

    /**
     * 退出登录
     * @return
     */
    @GetMapping("/user/logout")
    public ResponseResult logout() {
        return loginService.logout();
    }
}
