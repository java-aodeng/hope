package com.hope.service.impl;

import com.hope.mapper.SysRoleMapper;
import com.hope.model.SysRole;
import com.hope.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-10-08 11:30
 **/
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> SelectAll() {
        return sysRoleMapper.selectAll();
    }
}
