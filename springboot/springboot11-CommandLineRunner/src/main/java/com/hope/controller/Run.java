package com.hope.controller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @program:hope
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-11-01 19:07
 **/
@Component
@Order(-1)
public class Run implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Run");
    }
}
