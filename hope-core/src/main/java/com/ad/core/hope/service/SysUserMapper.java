package com.ad.core.hope.service;

import com.ad.core.hope.model.SysUser;
import java.util.List;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-10-05 13:20
 **/
public interface SysUserMapper {
    SysUser selectByPrimaryKey(Integer userid);

    List<SysUser> selectAll();
}