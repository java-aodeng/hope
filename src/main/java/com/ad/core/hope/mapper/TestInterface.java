package com.ad.core.hope.mapper;

import com.ad.core.hope.enums.TestUserEnum;
import com.ad.core.hope.vo.base.TestVo;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TestInterface {

    @Select("SELECT * FROM user")
    @Results({
            @Result(property = "sex", column = "sex", javaType = TestUserEnum.class),
            @Result(property = "name", column = "username")
    })
    List<TestVo> getAll();
}
