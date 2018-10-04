package com.ad.core.hope.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-09-02 17:05
 **/
public interface MyMapper<T> extends Mapper<T>,MySqlMapper<T> {
    //TODO
    //FIXME 该接口不能被扫描到，不然会报错
}
