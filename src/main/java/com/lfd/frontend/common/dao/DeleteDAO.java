package com.lfd.frontend.common.dao;

import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;

/**
 * Created by Administrator on 11/17/2016.
 */
public interface DeleteDAO<T, PK extends Serializable> {
    int delete(T record);

    int deleteByPrimaryKey(PK id);

    int deleteByExample(Example example);
}
