## 版权声明
>* 本文作者：低调小熊猫
本文链接：https://aodeng.cc/archives/springbootshi-si
* 版权声明：本文采用<a rel="license" href="http://creativecommons.org/licenses/by/4.0/">知识共享署名 4.0 国际许可协议</a>进行许可。转载请注明出处！
* 参考：[纯洁的微笑：Spring Boot 如何解决项目启动时初始化资源](http://www.ityouknow.com/springboot/2018/05/03/spring-boot-commandLineRunner.html "纯洁的微笑：Spring Boot 如何解决项目启动时初始化资源")

## 单纯的广告
>个人博客：https://aodeng.cc
微信公众号：低调小熊猫
QQ群：756796932

# 简介
**CommandLineRunner接口的Component会在spring bean初始化之后，SpringApplication run之前执行，可以控制在项目启动前初始化资源文件，比如初始化线程池，提前加载好加密证书等**

实现接口（CommandLineRunner）
@order表示加载顺序，-1，1，2，按照最小先执行的规则
Run类

````java
@Component
@Order(-1)
public class Run implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Run");
    }
}
````
我们多创建几个类实现接口
Run2类

```java
@Component
public class Run2 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Run2");
    }
}
```
Run3类

```java
@Component
@Order(1)
public class Run3 implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Run3");
    }
}
```
启动程序
```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.out.println("----------start--------");
        SpringApplication.run(Application.class,args);
        System.out.println("----------end--------");
    }
}
```
运行效果
（输出在start和end之间，说明CommandLineRunner 的执行时机，是在所有 Spring Beans 都初始化之后，SpringApplication.run() 之前执行，Run，Run3，Run2执行的顺序也是我们@order注解的顺序了）
```
----------start--------
Run
Run3
Run2
----------end--------
```
就是学习习惯做笔记了，这样印象深刻点，不论你在哪里看到我的文章，对你有帮助就好。