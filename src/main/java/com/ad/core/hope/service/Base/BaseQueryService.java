package com.ad.core.hope.service.Base;

import java.util.List;

public interface BaseQueryService <Vo extends ValueObject> {
    /****
     * 通过Id查找
     *
     * @param
     * @param id
     * @return
     * @throws Exception
     */
    Vo findOne(long id) throws Exception;

    /***
     * 翻页查询
     *
     * @param pageRequest
     * @param vo
     * @return
     * @throws Exception
     */
    //Page<Vo> findPage(Pageable pageRequest, Vo vo) throws Exception;

    /***
     * 列表查询
     *
     * @param vo
     * @return
     * @throws Exception
     */
    List<Vo> findAll(Vo vo) throws Exception;
}
