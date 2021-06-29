package com.hope.service.fallback;

import com.hope.service.TestFeign;
import org.springframework.stereotype.Component;

/**
 * @program:hope
 * @ClassName:TestFeignFallback
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @create:2019-03-16 15:33
 * @Description: 错误回调类 关联fallback = TestFeignFallback.class 一旦错误就回调同名称的方法
 * @Version 1.0
 **/
@Component
public class TestFeignFallback implements TestFeign{
    @Override
    public String testFegin() {
        return "Hystrix断路器容错机制启动";
    }

    @Override
    public String testByParam(String from) {
        return "Hystrix断路器容错机制启动";
    }
}
