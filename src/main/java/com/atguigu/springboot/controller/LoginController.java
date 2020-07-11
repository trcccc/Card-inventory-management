package com.atguigu.springboot.controller;

import com.atguigu.springboot.dao.*;
import com.atguigu.springboot.entities.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    AdminDao adminDao;

    @Autowired
    cardNumDao cardNumDao;
    @Autowired
    com.atguigu.springboot.dao.recordDao recordDao;

    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model,
                        Map<String,Object>map, HttpSession session){
                try {
                    Admin admin= adminDao.findById(username);
                    if(admin!=null&&admin.getPsw().equals(password)){
                        //登陆成功
                        session.setAttribute("loginUser",username);
                        List<Map<String, Object>> list=recordDao.getAll();
                        model.addAttribute("emps1",list);
                        String studentNum=cardNumDao.findByKind("学生卡").getNum();
                        String familyNum=cardNumDao.findByKind("家属卡").getNum();
                        String teacherNum=cardNumDao.findByKind("职工卡").getNum();
                        String temporaryNum=cardNumDao.findByKind("临时卡").getNum();
                        System.out.println("数"+studentNum);

                        model.addAttribute("num1",studentNum);
                        model.addAttribute("num2",familyNum);
                        model.addAttribute("num3",teacherNum);
                        model.addAttribute("num4",temporaryNum);
                        System.out.println(list);

                        return "emp/list";

                    }
                    else {
                        //登陆失败
                        map.put("msg", "用户名密码错误");
                        return "login";
                    }
                    }catch (Exception e ){
                    map.put("msg", "用户名错误");
                    return "login";
                }


            }






    }



