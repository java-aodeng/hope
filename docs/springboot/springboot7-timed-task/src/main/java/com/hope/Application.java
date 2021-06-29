package com.hope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-10-07 18:07
 **/
@SpringBootApplication
@EnableScheduling//启动类启用定时
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
