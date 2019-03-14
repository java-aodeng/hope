package com.hope.controller;

import com.hope.service.TestFeign;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TestFeign testFeign;

    @RequestMapping("/testFegin")
    public String testFeign(){
        return testFeign.testFegin();
    }
}
