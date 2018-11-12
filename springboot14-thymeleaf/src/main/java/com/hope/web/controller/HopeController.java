package com.hope.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @program:hope
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-11-12 15:44
 **/
@Controller
public class HopeController {
    @RequestMapping("/hope1")
    public String hope1(ModelMap map){
        map.addAttribute("hope1","Thymeleaf 赋值 start");
        map.addAttribute("hopeName","低调小熊猫很帅");
        map.addAttribute("hopes","hopes");
        List<User> userList=new ArrayList<>();
        User user=new User();
        user.setId(1);
        user.setName("admin");
        user.setPassword("123456");
        user.setSex("男");
        user.setAge(20);
        userList.add(user);
        map.addAttribute("userlist",userList);
        map.addAttribute("url","hope");
        map.addAttribute("number",1);
        map.addAttribute("switch","0");
        return "hope1";
    }
}
