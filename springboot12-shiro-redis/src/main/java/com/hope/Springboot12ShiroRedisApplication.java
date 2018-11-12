package com.hope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Springboot12ShiroRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot12ShiroRedisApplication.class, args);
	}
}
