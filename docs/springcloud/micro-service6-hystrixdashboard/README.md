## Hystrix Dashboard熔断监控面板-微服务架构
>Hystrix-dashboard是一款针对Hystrix进行实时监控的工具，通过Hystrix Dashboard我们可以在直观地看到各Hystrix Command的请求响应时间, 请求成功率等数据。

注册中心：[https://github.com/java-aodeng/hope/tree/master/micro-service1-eureka-server](https://github.com/java-aodeng/hope/tree/master/micro-service1-eureka-server)

服务提供者：[https://github.com/java-aodeng/hope/tree/master/micro-service2-eureka-provider](https://github.com/java-aodeng/hope/tree/master/micro-service2-eureka-provider)

服务消费者：[https://github.com/java-aodeng/hope/tree/master/micro-service5-feign](https://github.com/java-aodeng/hope/tree/master/micro-service5-feign)

这里以上面的注册中心，服务提供者，服务消费者为基础操作后续内容（代码已经开源，使用见文章）

## 创建HystrixDashboard项目
1.添加依赖
```
        <dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
		<!--hystrix-->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
		</dependency>
		<!--hystrix dashboard-->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
```
2.配置yml文件
```
#Hystrix Dashboard监控面板
server:
  port: 8889
spring:
  application:
    name: eureka-hystrixdashboard
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
      #healthcheck:
      #enabled: true  #开启自定义健康检查
  instance:
    #eureka服务器在接收到最后一个心跳之后等待的时间，然后才能从列表中删除此实例 默认90s（开发环境)
    lease-expiration-duration-in-seconds: 10
    #eureka客户端需要向eureka服务器发送心跳的频率 默认30s （开发环境)
    lease-renewal-interval-in-seconds: 1
#开启hystrix 指标
feign:
  hystrix:
    enabled: true
```
3.启动入口添加注解
```
@SpringBootApplication
@EnableEurekaClient
@EnableHystrixDashboard
public class MicroService6HystrixdashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroService6HystrixdashboardApplication.class, args);
	}

}
```
## 配置消费者eureka-feign项目
1.添加依赖
```
		<!--监控中心-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
```
2.配置yml文件
```
# 端点管理 hystrixDashboard
management:
  endpoints:
    web:
      exposure:
        include: "*"
```
## 启动项目测试

依此启动注册中心eureka-server,服务提供者eureka-provider,服务消费者eureka-feign,服务监控面板eureka-hystrixdashboard

1.访问监控面板 http://192.168.0.108:8889/hystrix 出现一只熊猫的页面 this is 服务监控面板eureka-hystrixdashboard
![](https://i.loli.net/2019/03/18/5c8f9faab0f44.png)

2.访问服务消费者 http://192.168.0.108:8888/testByParam/1 多访问几次

3.访问监控面板 http://192.168.0.108:8889/hystrix  在箭头1输入 http://192.168.0.108:8888/actuator/hystrix.stream 点击箭头2 出现如下图成功
![](https://i.loli.net/2019/03/18/5c8fa0ab325aa.png)

## 最后
但是只使用Hystrix Dashboard的话, 你只能看到单个应用内的服务信息, 这明显不够. 我们需要一个工具能让我们汇总系统内多个服务的数据并显示到Hystrix Dashboard上, 这个工具就是Turbine.
咳咳，正在研究，有点懵逼。。。

## 源码永久开源地址
[https://github.com/java-aodeng/hope/tree/master/micro-service6-hystrixdashboard](https://github.com/java-aodeng/hope/tree/master/micro-service6-hystrixdashboard)
