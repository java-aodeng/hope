package com.hope.quartz.scheduler;

import com.hope.quartz.job.TestJob;
import com.hope.quartz.job.TestJob2;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

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