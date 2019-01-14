package com.hope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient //服务消费者
public class MicroService3EurekaRibbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroService3EurekaRibbonApplication.class, args);
	}

}

