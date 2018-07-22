package com.ad.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-07-29 12:50
 **/
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.out.println("SpringBoot-hope Run!");
        SpringApplication.run(Application.class,args);
    }
}
