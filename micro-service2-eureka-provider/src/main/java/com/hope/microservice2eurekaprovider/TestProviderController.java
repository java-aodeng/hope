package com.hope.microservice2eurekaprovider;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program:hope
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2019-01-14 21:25
 **/
@RestController
public class TestProviderController {
    @RequestMapping("/test")
    public String test(){
        // 测试报错
        int a=1/0;
        return "my name is test 服务提供者";
    }

    @RequestMapping("/testByParam/{from}")
    public String testByParam(@PathVariable(value = "from") String from) {
        return "带参数:"+from;
    }
}
