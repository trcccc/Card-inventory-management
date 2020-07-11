package com.atguigu.springboot.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
         //判断是否有保存值，有即证明登录成功
        if(user!=null){
        //session不空，表示有保存值，有登陆，放行
        return true;
        }else{
        //没有值，没有登录，返回到登录页面：
            //同时设置错误信息
            request.setAttribute("msg","没有权限");
            request.getRequestDispatcher("/index.html").forward(request,response);

            return false;
    }



    }
}
