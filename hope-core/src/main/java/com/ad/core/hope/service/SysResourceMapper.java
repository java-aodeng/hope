package com.ad.core.hope.service;

import com.ad.core.hope.model.SysResource;
import java.util.List;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-10-05 13:20
 **/
public interface SysResourceMapper {
    SysResource selectByPrimaryKey(Integer resourceid);

    List<SysResource> selectAll();
}