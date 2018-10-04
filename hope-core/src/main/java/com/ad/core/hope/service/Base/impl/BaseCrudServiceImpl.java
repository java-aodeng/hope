package com.ad.core.hope.service.Base.impl;

import com.ad.core.hope.service.Base.ValueObject;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @program:hope
 * @author:aodeng
 * @create:2018-08-31 14:35
 **/
public class BaseCrudServiceImpl <Vo extends ValueObject> {

    @Autowired
    protected SqlSessionTemplate sqlSessionTemplate;
    @Autowired
    protected SqlSessionTemplate sqlSessionTemplateSqlserver;

    /**
     * 找到对应Mapper.xml中的SQL的id
     *
     * @param vo
     *            vo的命名要按照一定规则，以JsonVo举例，必须以Vo结束 insert时，JsonMapper.xml中，
     *            <insert>中的id应该是JsonMapper.save update时，JsonMapper.xml中，
     *            <update>中的id应该是JsonMapper.update delete时，JsonMapper.xml中，
     *            <delete>中的id应该是JsonMapper.deleteById
     * @param suffix
     * @return
     */
    /*public PageAndSortVo<Vo> convertToPageAndSortVo(Pageable pageRequest, Vo vo, boolean...isCount) {
        PageAndSortVo<Vo> pageVo = new PageAndSortVo<>();
        if (null != pageRequest) {
            pageVo.setOffset(pageRequest.getOffset());
            pageVo.setLimit(pageRequest.getPageSize());
            pageVo.setSort(getSort(pageRequest.getSort()));
            if(null!=isCount&&isCount.length>0){
                pageVo.setCount(isCount[0]);
            }
        } else {
            pageVo.setOffset(0);
            pageVo.setLimit(0);
        }
        pageVo.setVo(vo);
        return pageVo;

    }
    *//**
     * 组装排序的字段
     *
     * @param sort
     * @return
     *//*
    protected String getSort(Sort sort) {
        if (sort == null)
            return null;

        Iterator<Sort.Order> iterator = sort.iterator();
        String orderBySql = "";
        while (iterator.hasNext()) {
            Sort.Order order = iterator.next();
            String fieldName = order.getProperty();
            String sortDirection = order.getDirection().name();
            orderBySql += fieldName + " " + sortDirection + ",";
        }
        return orderBySql.substring(0, orderBySql.length() - 1);
    }*/
}
