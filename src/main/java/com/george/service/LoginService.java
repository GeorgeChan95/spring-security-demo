package com.george.service;

import com.george.domain.ResponseResult;
import com.george.entity.User;

/**
* <p></p>
*
* @author George
* @date 2024.05.25 16:50
*/
public interface LoginService {

    /**
     * 用户登录
     * @param user
     * @return
     */
    ResponseResult login(User user);
}
