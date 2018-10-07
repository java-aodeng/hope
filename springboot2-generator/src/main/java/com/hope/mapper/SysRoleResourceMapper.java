package com.hope.mapper;

import com.hope.model.SysRoleResource;
import java.util.List;

public interface SysRoleResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRoleResource record);

    SysRoleResource selectByPrimaryKey(Integer id);

    List<SysRoleResource> selectAll();

    int updateByPrimaryKey(SysRoleResource record);
}