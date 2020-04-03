package com.gem.bbs.filter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: jzhang
 * @WX: 15250420158
 * @Date: 2020/1/17 08:42
 * @Description:
 */
public class GlobalInterceptor implements HandlerInterceptor {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //获取应用路径
        modelAndView.addObject("contextPath",request.getContextPath());
    }
}
