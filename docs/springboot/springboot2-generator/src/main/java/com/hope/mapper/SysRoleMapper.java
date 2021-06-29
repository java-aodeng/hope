package com.hope.mapper;

import com.hope.model.SysRole;
import java.util.List;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer roleid);

    int insert(SysRole record);

    SysRole selectByPrimaryKey(Integer roleid);

    List<SysRole> selectAll();

    int updateByPrimaryKey(SysRole record);
}