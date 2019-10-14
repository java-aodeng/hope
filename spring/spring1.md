## Spring异步方法

首先，springboot启动类加上 @EnableAsync注解（@EnableAsync是spring支持的，这里方便举例使用springboot）。
```
 @SpringBootApplication
 @EnableAsync
 public class Application {
  public static void main(String[] args) {
  SpringApplication.run(Application.class, args);
  }
 }
```

复制代码其次，方法加上 @Async注解。
```
@Service
public class CreatingThread08Service {
 @Async
 public void call() {
 System.out.println(Thread.currentThread().getName() + " is running");
 }
}
```
运行结果如下：task-3 is running
```
task-2 is running
task-1 is running
task-4 is running
```
复制代码可以看到每次执行方法时使用的线程都不一样。使用Spring异步方法的方式，可以说是相当地方便，适用于前后逻辑不相关联的适合用异步调用的一些方法，比如发送短信的功能。

## 异步使用事务
  异步事务可同时使用
