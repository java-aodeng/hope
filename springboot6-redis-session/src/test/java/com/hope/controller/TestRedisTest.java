package com.hope.controller;

import com.hope.model.SysRole;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedisTest {
    private static final Logger log= LoggerFactory.getLogger(TestRedisTest.class);
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test//StringRedisTemplate使用的是 StringRedisSerializer
    public void test1() throws Exception{
        stringRedisTemplate.opsForValue().set("name","admin");
        Assert.assertEquals("admin",stringRedisTemplate.opsForValue().get("name"));
        log.info("[redis测试]-[{}]",stringRedisTemplate.opsForValue().get("name"));
    }
    @Test//RedisTemplate使用的是 JdkSerializationRedisSerializer
    public void test2() throws Exception{
        SysRole sysRole=new SysRole();
        sysRole.setRoleId(1);
        sysRole.setRole("1001");
        sysRole.setRole("管理员");
        sysRole.setDescription("这是一个测试管理员");
        ValueOperations<String,SysRole> valueOperations=redisTemplate.opsForValue();
        valueOperations.set("sysRole1",sysRole);
        valueOperations.set("sysRole2",sysRole,2, TimeUnit.SECONDS);
        Thread.sleep(1000);
        log.info("[sysRole2]-[{}]",valueOperations.get("sysRole2"));
        boolean exists=redisTemplate.hasKey("sysRole2");
        if(exists){
            System.out.println("true");
        }else {
            System.out.println("false");
        }
        Assert.assertEquals("管理员",valueOperations.get("sysRole1").getRole());
    }
}