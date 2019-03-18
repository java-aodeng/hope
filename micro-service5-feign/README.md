## Feign消费者与Hystrix断路器容错机制-微服务架构
注册中心：[https://github.com/java-aodeng/hope/tree/master/micro-service1-eureka-server](https://github.com/java-aodeng/hope/tree/master/micro-service1-eureka-server)

服务提供者：[https://github.com/java-aodeng/hope/tree/master/micro-service2-eureka-provider](https://github.com/java-aodeng/hope/tree/master/micro-service2-eureka-provider)

后面都以上面的注册中心，和服务提供者为基础操作后续内容（代码已经开源，使用方式见我上一篇and上上篇文章）
## 创建Feign与Hystrix
1.添加依赖
```
<!--feign star-->
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
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-openfeign</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<!--feign end-->
		<!--添加Hystrix依赖 断路器容错保护 -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
		</dependency>
```
2.配置yml文件
```
#配置服务消费者
server:
  port: 8888
spring:
  application:
    name: eureka-feign
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
#开启hystrix 指标
feign:
  hystrix:
    enabled: true
```
3.启动入口添加注解
```
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix//开启断路器功能
public class MicroService5FeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroService5FeignApplication.class, args);
	}

}
```
## 测试
用feign方式消费接口并添加Hystrix断路器

1.创建service层
```
/**
 * Feign消费服务接口,定义一个feign接口，通过@ FeignClient（“服务名”），来指定调用哪个服务。比如在代码中调用了eureka-provider服务的“/test”接口
 * 注意:服务名大小写无所谓，@FeignClient 会把接口类交给spring所管理,也就是说是可以@Autowired注入的
 * @program:hope
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2019-03-14 13:58
 **/
@FeignClient(value = "eureka-provider",fallback = TestFeignFallback.class)//关联fallback = TestFeignFallback.class 一旦错误就会调同名称的方法
public interface TestFeign {

    @RequestMapping("/test")
    String testFegin();

    @RequestMapping("/testByParam/{from}")
    String testByParam(@PathVariable(value = "from") String from);
}
```
2.创建fallback类，出错调用
```
/**
 * @program:hope
 * @ClassName:TestFeignFallback
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @create:2019-03-16 15:33
 * @Description: 错误回调类 关联fallback = TestFeignFallback.class 一旦错误就回调同名称的方法
 * @Version 1.0
 **/
@Component
public class TestFeignFallback implements TestFeign{
    @Override
    public String testFegin() {
        return "Hystrix断路器容错机制启动";
    }

    @Override
    public String testByParam(String from) {
        return "Hystrix断路器容错机制启动";
    }
}
```
3.创建controller使用
```
/**
 * @program:hope
 * @ClassName:TestFeignController
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @create:2019-03-14 13:58
 * @Description: TODO
 * @Version 1.0
 **/
@RestController
public class TestFeignController {

    @Autowired(required = false)
    private TestFeign testFeign;

    @RequestMapping("/testFegin")
    public String testFeign(){
        return testFeign.testFegin();
    }

    /** 
    * @Description: 传参数的接口需要加接收参数的注解，service也要加接收参数的注解，服务提供者provider也要加注解，3个接口必须保持一致
    * @Param: [from]
    * @return: [from]
    * @Author: aodeng
    * @Date: 19-3-15
    */ 
    @RequestMapping("/testByParam/{from}")
    public String testByParam(@PathVariable(value = "from") String from){
        return testFeign.testByParam(from);
    }
}
```
## 结果
当我们访问/testFegin接口时，会调用服务提供者的/test接口，这个接口人为制造异常，访问出错，页面出现：Hystrix断路器容错机制启动 表示成功

这就是Hystrix回退概念：当 断路器 打开或运行错误时，执行默认的代码，也就是我们的fallback的代码

当我们的服务提供者自身问题导致接口出现故障，防止出现雪崩效应，微服务架构提供了断路器等...保护机制。

## 源码永久开源
[https://github.com/java-aodeng/hope/tree/master/micro-service5-feign](https://github.com/java-aodeng/hope/tree/master/micro-service5-feign)