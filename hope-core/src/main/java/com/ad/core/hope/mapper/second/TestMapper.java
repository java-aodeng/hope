package com.ad.core.hope.mapper.second;

import com.ad.core.hope.model.admin.SysUser;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-09-02 17:25
 **/
@org.apache.ibatis.annotations.Mapper
public interface TestMapper{
    @Select("SELECT * FROM sys_user")
    @Results({
            @Result(property = "userid", column = "userId"),
            @Result(property = "username", column = "username")
    })
    List<SysUser> getAll();
}
