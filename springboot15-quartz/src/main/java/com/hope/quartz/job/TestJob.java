package com.hope.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

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
