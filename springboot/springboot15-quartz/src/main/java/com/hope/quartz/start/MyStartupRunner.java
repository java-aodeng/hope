package com.hope.quartz.start;

import com.hope.quartz.scheduler.CronScheduleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
