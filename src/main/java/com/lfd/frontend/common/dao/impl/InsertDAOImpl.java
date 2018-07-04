package com.lfd.frontend.common.dao.impl;

import com.lfd.frontend.common.dao.InsertDAO;

import java.io.Serializable;

/**
 * Created by Administrator on 11/17/2016.
 */
abstract public class InsertDAOImpl<T, PK extends Serializable> extends BaseDAOImpl<T> implements InsertDAO<T, PK> {
    @Override
    public int insert(T t) {
        return getMapper().insert(t);
    }

    @Override
    public int insertSelective(T t) {
        return getMapper().insertSelective(t);
    }
}
