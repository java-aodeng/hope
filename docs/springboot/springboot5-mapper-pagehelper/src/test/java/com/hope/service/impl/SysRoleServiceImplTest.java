package com.hope.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hope.model.SysRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysRoleServiceImplTest {
    private static final Logger log = LoggerFactory.getLogger(SysRoleServiceImplTest.class);

    @Autowired
    private SysRoleServiceImpl sysRoleService;

    @Test
    public void test1(){
        List<SysRole> sysRoleList=sysRoleService.SelectAll();
        log.info("[普通写法] - [{}]", sysRoleList);
        //分页
        PageInfo<Object> pageInfo= PageHelper.startPage(1,1).doSelectPageInfo(() -> sysRoleService.SelectAll());
        log.info("[分页]-[{}]",pageInfo);
    }
}