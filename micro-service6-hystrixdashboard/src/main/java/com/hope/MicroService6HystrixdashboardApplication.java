package com.hope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrixDashboard
public class MicroService6HystrixdashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroService6HystrixdashboardApplication.class, args);
	}

}
