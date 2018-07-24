package com.ad.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-07-19 12:50
 **/
@SpringBootApplication
@MapperScan("com.ad.core.mapper")
public class Application {
    public static void main(String[] args) {
        System.out.println("SpringBoot-hope Run!");
        SpringApplication.run(Application.class,args);
    }
}
