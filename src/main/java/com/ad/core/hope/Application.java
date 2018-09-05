package com.ad.core.hope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-07-19 12:50
 **/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@tk.mybatis.spring.annotation.MapperScan(basePackages = "com.ad.core.hope.mapper")
public class Application {
    public static void main(String[] args) {
        System.out.println("SpringBoot-hope Run!");
        SpringApplication.run(Application.class,args);
    }
}
