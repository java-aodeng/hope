## 版权声明
本作品采用<a rel="license" href="http://creativecommons.org/licenses/by/4.0/">知识共享署名 4.0 国际许可协议</a>进行许可。
本文作者：低调小熊猫
文章链接：https://aodeng.cc/archives/springbootshi
转载声明：自由转载-非商用-非衍生-保持署名，非商业转载请注明作者及出处，商业转载请联系作者本人qq:2696284032

## 单纯的广告
**个人博客：https://aodeng.cc**
**微信公众号：低调小熊猫**
**qq交流群：756796932**

## 简介
**定时任务，就是定时执行的程序，springboot是自己带的，所以创建springboot工程的配置就不贴了，直接看使用方法**
## 使用
**使用@EnableScheduling注解开启**
```
@SpringBootApplication
@EnableScheduling//启动类启用定时
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

}
```
**创建一个类，因为我们这个不是web实现类，也不是dao类，更不是service类，所以该类使用@Component注解**
```
@Component
public class TaskTestController {
    private static final Logger log= LoggerFactory.getLogger(TaskTestController.class);
    private int count=0;
    private static final SimpleDateFormat date=new SimpleDateFormat("HH:mm:ss");
    /**
     * @Scheduled(fixedRate = 6000) ：上一次开始执行时间点之后6秒再执行
     * @Scheduled(fixedDelay = 6000) ：上一次执行完毕时间点之后6秒再执行
     * @Scheduled(initialDelay=1000, fixedRate=6000) ：第一次延迟1秒后执行，之后按fixedRate的规则每6秒执行一次
     */
    @Scheduled(fixedDelay = 6000)
    public void test(){
        log.info("[count打印]-[{}]",count++);
    }
    @Scheduled(fixedDelay = 1000)
    public void test1(){
        log.info("[当前时间]-[{}]",date.format(new Date()));
    }
}
```
