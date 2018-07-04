package com.lfd.frontend.common.dao;

import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;

/**
 * Created by Administrator on 11/17/2016.
 */
public interface UpdateDAO<T, PK extends Serializable> {
    /**
     * 根据主键更新实体全部字段，null值会被更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(T record);

    /**
     * 根据Example条件更新实体`record`包含的全部属性，null值会被更新
     *
     * @param record
     * @param example
     * @return
     */
    int updateByExample(T record, Example example);

    /**
     * 根据Example条件更新实体`record`包含的不是null的属性值
     *
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(T record, Example example);

    /**
     * 根据主键更新属性不为null的值
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(T record);
}
