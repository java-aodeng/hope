package com.ad.core.hope.service.Base;

/**
 * 增删改查
 *
 * @author hlr
 *
 * @param <Vo>
 */
public interface BaseCrudService<Vo extends ValueObject> extends BaseQueryService<Vo>{
    /***
     * 保存
     * @param vo
     * @return
     * @throws Exception
     */
    Vo create(Vo vo) throws Exception;
    /***
     * 更新
     * @param vo
     * @return
     * @throws Exception
     */
    Vo update(Vo vo) throws Exception;
    /***
     * 通过Id删除
     * @param id
     * @throws Exception
     */
    void delete(long id) throws Exception;
}
