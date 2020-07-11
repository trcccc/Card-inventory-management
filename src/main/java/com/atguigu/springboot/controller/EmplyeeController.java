package com.atguigu.springboot.controller;

import com.atguigu.springboot.dao.*;
import com.atguigu.springboot.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static javax.print.attribute.standard.MediaPrintableArea.MM;

@Controller
public class EmplyeeController {

    @Autowired
    recordDao recordDao;
    @Autowired
    cardNumDao cardNumDao;
    @Autowired
    AdminDao adminDao;



    //假装退出
    @GetMapping("/index")
    public String getout(Model model){

        return "login";
    }
    //查询所有校园卡信息返回列表页面
    @GetMapping("/emps1")
    public String list(Model model){
        List<Map<String, Object>> list=recordDao.getAll();
        String studentNum=cardNumDao.findByKind("学生卡").getNum();
        String familyNum=cardNumDao.findByKind("家属卡").getNum();
        String teacherNum=cardNumDao.findByKind("职工卡").getNum();
        String temporaryNum=cardNumDao.findByKind("临时卡").getNum();
        System.out.println("数"+studentNum);
        model.addAttribute("emps1",list);
        model.addAttribute("num1",studentNum);
        model.addAttribute("num2",familyNum);
        model.addAttribute("num3",teacherNum);
        model.addAttribute("num4",temporaryNum);

//        System.out.println(list);
        return "emp/list";
    }

    @GetMapping("/emp1")
    public String toAddPage(Model model){

        return "emp/add";
    }

    @PostMapping("/emp1")
    public String addStu(record record){
        Date date=new Date();
        SimpleDateFormat dateFormat_min=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        System.out.println(dateFormat_min.format(date));
        record.setTime(dateFormat_min.format(date));
        System.out.println(record);

        cardNumDao.inCrease(Integer.parseInt(record.getNum()) ,record.getKind());


        //保存
        System.out.println(recordDao.save(record));
        return "redirect:/emps1";
    }


    @PostMapping("/select/stu")
    public String selectStu(@RequestParam("id") String id,
                            @RequestParam("department") String depa,Model model){
        if (id!="")
        {

            List<Map<String, Object>> list=recordDao.findByTime(id);
            model.addAttribute("emps1",list);
        }else{

            System.out.println(id);
            if (depa.equals("全部"))
            {
                System.out.println("进入");
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
                return "emp/list";
            }

            List<Map<String, Object>> list=recordDao.findByDepartment(depa);
            model.addAttribute("emps1",list);

        }
        String studentNum=cardNumDao.findByKind("学生卡").getNum();
        String familyNum=cardNumDao.findByKind("家属卡").getNum();
        String teacherNum=cardNumDao.findByKind("职工卡").getNum();
        String temporaryNum=cardNumDao.findByKind("临时卡").getNum();
        System.out.println("数"+studentNum);

        model.addAttribute("num1",studentNum);
        model.addAttribute("num2",familyNum);
        model.addAttribute("num3",teacherNum);
        model.addAttribute("num4",temporaryNum);

        return "emp/list";
    }



    @GetMapping("/changepsw")
    public String stuPsw(Model model){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//已经拿到session,就可以拿到session中保存的用户信息了。
        System.out.println(request.getSession().getAttribute("loginUser"));
        String id=request.getSession().getAttribute("loginUser").toString();

        return "change";
    }

    @PostMapping("/change")
    public String changePsw(@RequestParam("newpsw") String psw,@RequestParam("renewpsw") String renewpsw,Model model,Map<String,Object>map) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String id = request.getSession().getAttribute("loginUser").toString();

        Admin list = adminDao.findById(id);
        list.setPsw(renewpsw);
        if (psw.equals(renewpsw)) {
            adminDao.changepsw(list, renewpsw);
            map.put("msg", "修改成功！");
        } else {
            map.put("msg", "先后输入密码不相同，请重新输入");
        }
        System.out.println(list);
        return "change";


    }
}
