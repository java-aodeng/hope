# Spring Boot(十七)： 集成 Quartz

 **以前我写过spring自带的定时任务**
SpringBoot(十)：定时任务 https://aodeng.cc/archives/springbootshi

**很方便也很简单，掌握cron表达式就行，cron 说明**

cron 一共有七位，最后一位是年，Spring Boot 定时方案中只需要设置六位即可：

第一位，表示秒，取值 0 ~ 59/
第二位，表示分，取值 0 ~ 59/
第三位，表示小时，取值 0 ~ 23/
第四位，日期天/日，取值 1 ~ 31/
第五位，日期月份，取值 1~12/
第六位，星期，取值 1 ~ 7，星期一，星期二...，注，不是第 1 周、第 2 周的意思，另外，1 表示星期天，2 表示星期一/
第七位，年份，可以留空，取值 1970 ~ 2099/

## 简单介绍一下Quartz
Job 为作业的接口，为任务调度的对象；JobDetail 用来描述 Job 的实现类及其他相关的静态信息；Trigger 做为作业的定时管理工具，一个 Trigger 只能对应一个作业实例，而一个作业实例可对应多个触发器；Scheduler 做为定时任务容器，是 Quartz 最上层的东西，它提携了所有触发器和作业，使它们协调工作，每个 Scheduler 都存有 JobDetail 和 Trigger 的注册，一个 Scheduler 中可以注册多个 JobDetail 和多个 Trigger。

**使用**：
我们创建两个job
TestJob
```
/**
 * @program:hope
 * @ClassName:TestJob
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @create:2019-02-26 10:17
 * @Description: TODO
 * @Version 1.0
 **/
public class TestJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("TestJob running ...");
    }
}
```
TestJob2 
```
/**
 * @program:hope
 * @ClassName:TestJob2
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @create:2019-02-26 10:18
 * @Description: TODO
 * @Version 1.0
 **/
public class TestJob2 implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("TestJob2 running ...");
    }
}
```

创建CronScheduleJob

```
/**
 * @program:hope
 * @ClassName:CronScheduleJob
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @create:2019-02-26 10:21
 * @Description: TODO
 * @Version 1.0
 **/
@Component
public class CronScheduleJob {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    /** 
    * @Description: 启动两个定时任务
    * @Param: []
    * @return: []
    * @Author: aodeng
    * @Date: 19-2-26
    */ 
    public void scheduleJobs() throws SchedulerException{
        Scheduler scheduler=schedulerFactoryBean.getScheduler();
        schedulejobs(scheduler);
        schedulejobs2(scheduler);

    }
    private void schedulejobs(Scheduler scheduler) throws SchedulerException{
        JobDetail jobDetail = JobBuilder.newJob(TestJob.class).withIdentity("job","group").build();
        //间隔 6 秒
        CronScheduleBuilder scheduleBuilder=CronScheduleBuilder.cronSchedule("0/6 * * * * ?");
        CronTrigger cronTrigger=TriggerBuilder.newTrigger().withIdentity("trigger","group").withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
    }
    private void schedulejobs2(Scheduler scheduler) throws SchedulerException{
        JobDetail jobDetail = JobBuilder.newJob(TestJob2.class).withIdentity("job2","group2").build();
        //间隔 12 秒
        CronScheduleBuilder scheduleBuilder=CronScheduleBuilder.cronSchedule("0/12 * * * * ?");
        CronTrigger cronTrigger=TriggerBuilder.newTrigger().withIdentity("trigger2","group2").withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
    }
}
```

启动

```
/**
 * @program:hope
 * @ClassName:MyStartupRunner
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @create:2019-02-26 10:38
 * @Description: 启动时触发定时任务
 * @Version 1.0
 **/
@Component
public class MyStartupRunner implements CommandLineRunner{
    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(MyStartupRunner.class);

    @Autowired
    private CronScheduleJob cronScheduleJob;

    @Override
    public void run(String... args) throws Exception {
        cronScheduleJob.scheduleJobs();
        LOGGER.info(">>>>>>>>>>>>>>>定时任务开始执行<<<<<<<<<<<<<");
    }
}
```
