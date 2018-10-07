package com.hope.mapper;

import com.hope.model.SysUserRole;
import java.util.List;

public interface SysUserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUserRole record);

    SysUserRole selectByPrimaryKey(Integer id);

    List<SysUserRole> selectAll();

    int updateByPrimaryKey(SysUserRole record);
}