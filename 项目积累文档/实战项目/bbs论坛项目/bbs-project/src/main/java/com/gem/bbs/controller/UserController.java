package com.gem.bbs.controller;

import com.gem.bbs.entity.User;
import com.gem.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @Author: jzhang
 * @WX: 15250420158
 * @Date: 2020/2/13 08:56
 * @Description: 用户控制器
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //去往登录界面
    @RequestMapping("/loginPage")
    public String loginPage() {
        return "login";
    }

    //去往注册界面
    @RequestMapping("/registerPage")
    public String registerPage() {
        return "register";
    }

    //登录请求
    @ResponseBody
    @RequestMapping("/login")
    public String login(String loginname, String password, HttpSession session) {
        String flag = userService.login(loginname,password,session);
        //判断flag的结果，跳转至不同的视图（继续停留在登录界面，跳转至首页）
        //用户登录成功将，用户信息添加到session
//        if (flag.equals("101")) {
//            session.setAttribute("user",loginname);
//        }
        return flag;
    }

    //注册请求
    @ResponseBody
    @RequestMapping("/register")
    public String register(User user) {
        user.setCreatetime(new Date());
        userService.register(user);
        return "success";
    }

    //安全退出
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index";
    }
}
