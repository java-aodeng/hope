package com.ad.core.vo.base;

import com.ad.core.enums.TestUserEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-07-24 17:14
 **/
@Getter
@Setter
public class TestVo {
    private long id;
    private String name;
    private TestUserEnum sex;
}
