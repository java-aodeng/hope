package com.hope.quartz.scheduler;

import com.hope.quartz.job.SampleJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program:hope
 * @ClassName:SampleScheduler
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @create:2019-02-26 09:56
 * @Description: 容器
 * @Version 1.0
 **/
@Configuration
public class SampleScheduler {

    @Bean
    public JobDetail sampleDetail() {
        return JobBuilder.newJob(SampleJob.class).withIdentity("sampleJob")
                .usingJobData("name", "低调小熊猫").storeDurably().build();
    }

    @Bean
    public Trigger sampleJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(2).repeatForever();
        return TriggerBuilder.newTrigger().forJob(sampleDetail())
                .withIdentity("sampleTrigger").withSchedule(scheduleBuilder).build();
    }

}