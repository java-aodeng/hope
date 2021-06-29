## 常用注解
>熊猫笔记 https://github.com/java-aodeng/hope

    事务，异步处理
    @Transactional(rollbackFor = Exception.class)
    @Async
    
    把普通pojo实例化到spring容器中
    @Component
    
    实现dao访问
    @Repository
    
    服务 放在实现类上面
    @Service
    
    控制器
    @Controller
    
    Dubbo的service和Reference：
        服务提供者：
        @Component
        @Service(version = "1.0.0")
        
        服务消费端
        @Reference(timeout = 9000, version = "1.0.0")
        
    用于定义配置类
    @Configuration
    
    用于在依赖关系注入完成之后需要执行的方法上，以执行任何初始化
    @PostConstruct
    
    定义一个切面类 ：
        放在类的上面配合@Component注解使用
        @Component
        @Aspect
        
        Around属于环绕增强，能控制切点执行前，执行后，用这个注解后，程序抛异常，会影响@AfterThrowing这个注解
        @Around("execution(* com.baidu..controller.CenterController.action(..))")
        
    表示该属性在方法或类中没有使用。添加此注解可以去除属性上的黄色警告！（猫哥注解：就是没使用的方法是灰色的，将其变成黄色的，看起像被使用的方法一样）
    @SuppressWarnings("unused")
