package com.hope.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program:hope
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-11-08 20:40
 **/
public class TestController {
    private static final Logger log= LoggerFactory.getLogger(TestController.class);
    public static void main(String[] args) {
        Cal cal=new Cal();
        //使用加法
        cal.setOperation(new OperationAdd());
        //输出结果
        log.info("[计算器加]-[{}]",cal.doOperation(4,2));

        //使用减法
        cal.setOperation(new OperationSub());
        //输出结果
        log.info("[计算器减]-[{}]",cal.doOperation(4,2));
    }
}
