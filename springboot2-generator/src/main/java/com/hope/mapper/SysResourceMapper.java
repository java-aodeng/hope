package com.hope.mapper;

import com.hope.model.SysResource;
import java.util.List;
public interface SysResourceMapper {
    int deleteByPrimaryKey(Integer resourceid);

    int insert(SysResource record);

    SysResource selectByPrimaryKey(Integer resourceid);

    List<SysResource> selectAll();

    int updateByPrimaryKey(SysResource record);
}