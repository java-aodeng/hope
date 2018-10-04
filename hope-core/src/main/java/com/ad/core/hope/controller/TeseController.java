package com.ad.core.hope.controller;

import cn.hutool.core.util.RandomUtil;
import com.ad.core.hope.mapper.first.TestInterface;
import com.ad.core.hope.mapper.second.TestMapper;
import com.ad.core.hope.model.admin.SysUser;
import com.ad.core.hope.service.admin.SysUserMapper;
import com.ad.core.hope.service.project.TestUserService;
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
    @Autowired
    private TestUserService testUserService;
    @Autowired
    private TestMapper testMapper;

    @RequestMapping("/index")
    public String index(){
        System.out.println("this is test!");
        return "admin/TSindex";
    }
    @RequestMapping("/login")
    public String login(Model model){
        System.out.println("测试热启动");
        //数据源first测试
        List<TestVo> list=testInterface.getAll();
        for(int i=0;i<list.size();i++){
            System.out.println("测试fisrt数据源"+list.get(i).getName());
        }

        //hutool测试
        int bgnumber= RandomUtil.randomInt(5);
        System.out.println("测试hutool工具:"+bgnumber);
        model.addAttribute("bgnumber",bgnumber+1);

        //数据源second测试
        List<SysUser> user=testMapper.getAll();
        for(int i=0;i<user.size();i++){
            System.out.println("测试second数据源"+user.get(i).getUsername());
        }

        return "admin/system_login";
    }
    @RequestMapping("/system_index")
    public String system_index(Model model){
      /*  //数据源second测试
        List<SysUser> user=testMapper.getAll();
        for(int i=0;i<user.size();i++){
            System.out.println("测试fisrt数据源"+user.get(i).getUsername());
        }*/
        return "admin/system_index";
    }
    @RequestMapping("/system_index_v1")
    public String system_index_v1(Model model){
        return "admin/system_index_v1";
    }
}
