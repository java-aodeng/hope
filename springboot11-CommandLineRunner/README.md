### 创建Eureka client
**1.创建项目**\
new module时选择cloud Discovery，右边勾选Eureka Discovery，最后finish即可。\
**2.启动入口，添加注解 @EnableEurekaClient**\
hello 接口用于测试
```
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
```
**3.配置文件,注册服务**
```
server:
  port: 8762
spring:
  application:
    name: eureka-client
  cloud:
    inetutils:
      ignored-interfaces:             #忽略docker0网卡以及 veth开头的网卡
        - docker0
        - veth.*
      preferred-networks:             #使用正则表达式,使用指定网络地址
        - 192.168
        - 10.0
  profiles:
    active: eureka

eureka:
  instance:
    hostname: localhost
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka
```
**4.运行项目**
```
访问：http://localhost:8761/ 看到Application哪里有eureka-client服务了，表示成功了
```
**5.测试**
``` 
访问： http://localhost:8762/hello?name=低调小熊猫 
页面返回：hello world,my name is低调小熊猫 表示成功
```