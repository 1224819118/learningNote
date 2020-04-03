package com.gem.bbs.service;

import com.gem.bbs.entity.User;

import javax.servlet.http.HttpSession;

/**
 * @Author: jzhang
 * @WX: 15250420158
 * @Date: 2020/2/13 08:48
 * @Description:
 */
public interface UserService {
    /**
     * 注册用户
     */
    void register(User user);

    /**
     * 用户登录
     */
    String login(String loginname,String password,HttpSession session);
}
