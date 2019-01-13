package com.hope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class MicroService1EurekaClientApplication {

	@RequestMapping("/hello")
	public String hello(@RequestParam String name){
		return "hello world,my name is"+name;
	}
	public static void main(String[] args) {
		SpringApplication.run(MicroService1EurekaClientApplication.class, args);
	}

}

