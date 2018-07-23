package com.ad.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-07-19 14:20
 **/
@Controller
public class TeseController {
    @RequestMapping("/index")
    public String index(){
        System.out.println("this is test!");
        return "admin/TSindex";
    }
    @RequestMapping("/login")
    public String login(){
        return "admin/login";
    }
}
