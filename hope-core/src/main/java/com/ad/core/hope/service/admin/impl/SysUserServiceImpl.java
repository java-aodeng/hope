package com.ad.core.hope.service.admin.impl;

import com.ad.core.hope.model.admin.SysUser;
import com.ad.core.hope.service.Base.impl.BaseCrudServiceImpl;
import com.ad.core.hope.service.admin.SysUserMapper;

import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-08-31 14:25
 **/
@Service
public class SysUserServiceImpl implements SysUserMapper{
    @Override
    public int deleteByPrimaryKey(Integer userid) {
        return 0;
    }

    @Override
    public int insert(SysUser record) {
        return 0;
    }

    @Override
    public SysUser selectByPrimaryKey(Integer userid) {
        return null;
    }

    @Override
    public List<SysUser> selectAll() {
        return null;
    }

    @Override
    public int updateByPrimaryKey(SysUser record) {
        return 0;
    }
}
