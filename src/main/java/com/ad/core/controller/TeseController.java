package com.ad.core.controller;

import com.ad.core.mapper.TestInterface;
import com.ad.core.vo.base.TestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-07-19 14:20
 **/
@Controller
public class TeseController {

    @Autowired
    private TestInterface testInterface;

    @RequestMapping("/index")
    public String index(){
        System.out.println("this is test!");
        return "admin/TSindex";
    }
    @RequestMapping("/login")
    public String login(){
        System.out.println("热启动测试");
        List<TestVo> list=testInterface.getAll();
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i).getName());
        }
        return "admin/login";
    }
}
