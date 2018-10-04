package com.ad.core.hope.service.project.impl;

import com.ad.core.hope.mapper.second.TestMapper;
import com.ad.core.hope.model.admin.SysUser;
import com.ad.core.hope.service.project.TestUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-09-02 17:33
 **/
@Service
public class TestUserServiceImpl implements TestUserService{

    @Autowired
    private TestMapper testUserMapper;
    @Override
    public SysUser selectUsers() {
        return null;
    }
}
