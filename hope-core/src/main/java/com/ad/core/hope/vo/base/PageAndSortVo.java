package com.ad.core.hope.vo.base;

import com.ad.core.hope.service.Base.ValueObject;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-08-31 15:07
 **/
@Data
@Accessors
public class PageAndSortVo<Vo> implements ValueObject{
    private static final long serialVersionUID = 1L;

    /**
     * 对应表中的起始记录
     */
    private int offset;

    /**
     * 要取的记录数，pageNumber
     */
    private int limit=10;

    private String sort;
    private boolean count = false;
    private Map<String,Double> sumPd = null;

    private Vo vo;
    private int totalResult;
}
