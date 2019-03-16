package com.hope.service;

import com.hope.service.fallback.TestFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Feign消费服务接口,定义一个feign接口，通过@ FeignClient（“服务名”），来指定调用哪个服务。比如在代码中调用了eureka-provider服务的“/test”接口
 * 注意:服务名大小写无所谓，@FeignClient 会把接口类交给spring所管理,也就是说是可以@Autowired注入的
 * @program:hope
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2019-03-14 13:58
 **/
@FeignClient(value = "eureka-provider",fallback = TestFeignFallback.class)//关联fallback = TestFeignFallback.class 一旦错误就回调同名称的方法
public interface TestFeign {

    @RequestMapping("/test")
    String testFegin();

    @RequestMapping("/testByParam/{from}")
    String testByParam(@PathVariable(value = "from") String from);
}