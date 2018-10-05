package com.ad.core.hope.service;

import com.ad.core.hope.model.SysUserRole;
import java.util.List;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-10-05 13:20
 **/
public interface SysUserRoleMapper {
    SysUserRole selectByPrimaryKey(Integer id);

    List<SysUserRole> selectAll();
}