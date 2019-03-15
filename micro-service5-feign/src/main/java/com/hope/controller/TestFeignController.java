package com.hope.controller;

import com.hope.service.TestFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program:hope
 * @ClassName:TestFeignController
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @create:2019-03-14 13:58
 * @Description: TODO
 * @Version 1.0
 **/
@RestController
public class TestFeignController {

    @Autowired(required = false)
    private TestFeign testFeign;

    @RequestMapping("/testFegin")
    public String testFeign(){
        return testFeign.testFegin();
    }

    /** 
    * @Description: 传参数的接口需要加接收参数的注解，service也要加接收参数的注解，服务提供者provider也要加注解，3个接口必须保持一致
    * @Param: [from]
    * @return: [from]
    * @Author: aodeng
    * @Date: 19-3-15
    */ 
    @RequestMapping("/testByParam/{from}")
    public String testByParam(@PathVariable(value = "from") String from){
        return testFeign.testByParam(from);
    }
}
