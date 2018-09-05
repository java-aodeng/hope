package com.ad.core.hope.vo.base;

import com.ad.core.hope.enums.TestUserEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-07-24 17:14
 **/
@Data
@Accessors(chain = true)
public class TestVo {
    private long id;
    private String name;
    private TestUserEnum sex;
}
