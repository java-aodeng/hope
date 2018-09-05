package com.ad.core.hope.mapper.second;

import com.ad.core.hope.enums.TestUserEnum;
import com.ad.core.hope.model.admin.SysUser;
import com.ad.core.hope.util.MyMapper;
import com.ad.core.hope.vo.base.TestVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-09-02 17:25
 **/
@Mapper
public interface TestMapper{
    /**
     * 测试
     */

    @Select("SELECT * FROM sys_user")
    @Results({
            @Result(property = "name", column = "username")
    })
    List<TestVo> getAll();
}
