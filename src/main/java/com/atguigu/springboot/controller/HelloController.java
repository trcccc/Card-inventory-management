package com.atguigu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class HelloController
{
//    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "Hello world";
    }

    //查出一些数据，在页面展示
    @RequestMapping("/success")
    public String success(Map<String,Object> map){
        //classpath:/templates/success.html
        map.put("hello","你好");
        return "success";
    }
    @RequestMapping("/ht")
    public String ht(Model model) {
        model.addAttribute("title","hello Thymeleaf")
                .addAttribute("info","this is first thymeleaf test");
        return "t1";
    }
}
