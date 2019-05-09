package com.hope.mapper;

import com.hope.model.SysUser;
import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(SysUser record);

    SysUser selectByPrimaryKey(Integer userid);

    List<SysUser> selectAll();

    int updateByPrimaryKey(SysUser record);
}