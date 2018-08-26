package com.ad.core.hope.controller;

import cn.hutool.core.util.RandomUtil;
import com.ad.core.hope.mapper.TestInterface;
import com.ad.core.hope.vo.base.TestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String login(Model model){
        System.out.println("热启动测试");
        List<TestVo> list=testInterface.getAll();
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i).getName());
        }
        int bgnumber= RandomUtil.randomInt(5);
        System.out.println(bgnumber);
        model.addAttribute("bgnumber",bgnumber+1);
        return "admin/login";
    }

}
