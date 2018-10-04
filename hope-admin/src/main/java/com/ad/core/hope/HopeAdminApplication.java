package com.ad.core.hope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-07-19 12:50
 **/
@SpringBootApplication
public class HopeAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(HopeAdminApplication.class,args);
    }
}
