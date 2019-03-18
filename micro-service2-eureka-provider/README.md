## 服务提供者(provider)
1.创建项目
```
	<description>服务提供</description>
	<dependencies>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
	</dependencies>

```
2.开启提供者
```
@SpringBootApplication
@EnableDiscoveryClient //注册服务到注册中心去
public class MicroService2EurekaProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroService2EurekaProviderApplication.class, args);
	}

}
```
3.配置yml文件，注册服务
```
#配置服务提供者
server:
  port: 8886
spring:
  application:
    name: eureka-provider
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
```
4.创建controller
```
/**
 * @program:hope
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2019-01-14 21:25
 **/
@RestController
public class TestProviderController {
    @RequestMapping("/test")
    public String test(){
        return "my name is test 服务提供者";
    }
}
```
5.测试
```
访问  http://localhost:8761/ 看到eureka-provider服务表示注册成功
```