package com.atguigu.springboot.config;

import com.atguigu.springboot.component.LoginHandlerInterceptor;
import com.atguigu.springboot.component.MyLocalReolver;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;


//继承接口WebMvcConfigurer实现管理SpringMvc
@Configuration
    public class MyMvcConfig implements WebMvcConfigurer {

 //第一种实现方法：编写addViewControllers方法
         @Override
         public void addViewControllers(ViewControllerRegistry registry) {
            //将login.html映射到路径urlpath为："/"上
        registry.addViewController("/").setViewName("login");
        }

 //第二种实现方法：添加WebMvcConfigurer组件
        @Bean
        public WebMvcConfigurer webMvcConfigurer() {
        WebMvcConfigurer adapter = new WebMvcConfigurer() {
        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            //将login.html映射到路径urlpath为："/"上
            registry.addViewController("/").setViewName("login");
            registry.addViewController("/index.html").setViewName("login");
            registry.addViewController("/main.html").setViewName("/emp/list");
            registry.addViewController("/student.html").setViewName("student");
            registry.addViewController("/teacher.html").setViewName("teacher");
            registry.addViewController("/family.html").setViewName("family");
            }
            @Override
         public void addInterceptors(InterceptorRegistry registry) {
            //排除"/"下的全部路径，除了"/login.html","/","/user/login"
            registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                    .excludePathPatterns("/index.html","/","/user/login","/static/**");
         }

        @Override
         public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
            WebMvcConfigurer.super.addResourceHandlers(registry);
        }


        };
        return adapter;
    }
}
