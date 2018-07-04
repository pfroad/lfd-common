package com.lfd.frontend.common.dao.impl;

import com.lfd.frontend.common.dao.UpdateDAO;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;

/**
 * Created by Administrator on 11/17/2016.
 */
abstract public class UpdateDAOImpl<T, PK extends Serializable> extends BaseDAOImpl<T> implements UpdateDAO<T, PK> {
    public int updateByPrimaryKey(T record) {
        return getMapper().updateByPrimaryKey(record);
    }

    public int updateByExample(T record, Example example) {
        return getMapper().updateByExample(record, example);
    }

    public int updateByExampleSelective(T record, Example example) {
        return getMapper().updateByExampleSelective(record, example);
    }

    public int updateByPrimaryKeySelective(T record) {
        return getMapper().updateByPrimaryKeySelective(record);
    }
}
