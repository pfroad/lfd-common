package com.lfd.frontend.common.dao.impl;

import com.lfd.frontend.common.dao.DeleteDAO;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;

/**
 * Created by Administrator on 11/17/2016.
 */
abstract public class DeleteDAOImpl<T, PK extends Serializable> extends BaseDAOImpl<T> implements DeleteDAO<T, PK> {
    public int delete(T record) {
        return getMapper().delete(record);
    }

    public int deleteByPrimaryKey(PK id) {
        return getMapper().deleteByPrimaryKey(id);
    }

    public int deleteByExample(Example example) {
        return getMapper().deleteByExample(example);
    }
}