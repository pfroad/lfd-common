package com.lfd.frontend.common.dao;

import java.io.Serializable;

/**
 * Created by Administrator on 11/17/2016.
 */
public interface InsertDAO<T, PK extends Serializable> {
    /**
     * 存一个实体，null的属性会保存，不使用数据库默认值
     * @param t
     * @return
     */
    public int insert(T t);

    /**
     * 存一个实体，null的属性不会保存，会使用数据库默认值
     * @param t
     * @return
     */
    public int insertSelective(T t);
}
