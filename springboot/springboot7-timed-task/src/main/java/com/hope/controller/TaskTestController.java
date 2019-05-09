package com.hope.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-10-09 15:15
 **/
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
