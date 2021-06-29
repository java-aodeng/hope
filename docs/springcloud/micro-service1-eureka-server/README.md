### Eureka server
**1.创建项目**
强烈推荐IDEA创建项目，好出是，自动帮我们配置好依赖
new module时选择cloud Discovery，右边勾选Eureka Server，最后finish即可。
**2.启动入口，添加注解 @EnableEurekaServer**
```
@SpringBootApplication
@EnableEurekaServer
public class MicroService1Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroService1Application.class, args);
	}

}
```
**3.配置文件**
```
#在默认设置下，该服务注册中心也会将自己作为客户端来尝试注册它自己，所以我们需要禁用它的客户端注册行为，在application.yml添加以下配置：
server:
  port: 8761  	#采用官方端口，瞎写也行
spring:
  application:
    name: eureka-server
  cloud:
    inetutils:
      ignored-interfaces:             #忽略docker0网卡以及 veth开头的网卡
        - docker0
        - veth.*
      preferred-networks:             #使用正则表达式,使用指定网络地址
        - 192.168
        - 10.0
#表示是否将自己注册到Eureka Server，默认为true
eureka:
  instance:
    hostname: localhost 	#配置主机名
  client:
    register-with-eureka: false
#表示是否从Eureka Server获取注册信息，默认为true
    fetch-registry: false
#设置与Eureka Server交互的地址，查询服务和注册服务都需要依赖这个地址。默认是http://localhost:8761/eureka ；多个地址可使用 , 分隔
    serviceUrl:
      defaultZone: http://localhost:${server.port}/eureka/
```
**4.运行项目**
```
访问：http://localhost:8761/ 看到spring Eureka，表示成功了
```