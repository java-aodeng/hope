package com.hope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-10-09 16:50
 **/
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.out.println("----------start--------");
        SpringApplication.run(Application.class,args);
        System.out.println("----------end--------");
    }
}
