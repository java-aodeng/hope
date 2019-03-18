## 服务消费者(ribbon版本)
1.创建项目
```
<description>服务消费者</description>
	<dependencies>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
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
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
	</dependencies>
```
2.开启消费者
```
@SpringBootApplication
@EnableDiscoveryClient //服务消费者
public class MicroService3EurekaRibbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroService3EurekaRibbonApplication.class, args);
	}

}
```
3.配置yml文件，注册服务
```
#配置服务消费者
server:
  port: 8887
spring:
  application:
    name: eureka-ribbon
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
 * @create:2019-01-14 21:46
 **/
@RestController
public class TestRibbonController {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/test")
    public String test(){
        return restTemplate.getForObject("http://eureka-provider/test",String.class);
    }
}
```
5.测试
```
访问  http://192.168.0.101:8887/test  页面返回my name is test 服务提供者 表示成功
```