package com.hope.microservice2eurekaprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient //注册服务到注册中心去
public class MicroService2EurekaProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroService2EurekaProviderApplication.class, args);
	}

}

