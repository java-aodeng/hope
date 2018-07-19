package com.ad.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.out.println("SpringBoot2.1.0 Application Run!");
        SpringApplication.run(Application.class,args);
    }
}
