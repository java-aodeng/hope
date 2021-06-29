package com.hope.controller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @program:hope
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-11-01 19:10
 **/
@Component
public class Run2 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Run2");
    }
}
