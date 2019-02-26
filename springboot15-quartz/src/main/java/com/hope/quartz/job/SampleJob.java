package com.hope.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @program:hope
 * @ClassName:SampleJob
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @create:2019-02-26 09:45
 * @Description: 接口
 * @Version 1.0
 **/
public class SampleJob extends QuartzJobBean{

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(String.format("Hello %s!",this.name));
    }
}
