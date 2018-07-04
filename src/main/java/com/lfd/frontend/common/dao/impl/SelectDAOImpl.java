package com.lfd.frontend.common.dao.impl;

import com.lfd.frontend.common.dao.SelectDAO;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 11/17/2016.
 */
abstract public class SelectDAOImpl<T, PK extends Serializable> extends BaseDAOImpl<T> implements SelectDAO<T, PK> {
    public T selectByPrimaryKey(PK id) {
        return getMapper().selectByPrimaryKey(id);
    }

    public List<T> selectAll() {
        return getMapper().selectAll();
    }

    public List<T> select(T record) {
        return getMapper().select(record);
    }

    public List<T> selectByExample(Example example) {
        return getMapper().selectByExample(example);
    }

    public List<T> selectByExampleAndRowBounds(Example example, RowBounds rowBounds) {
        return getMapper().selectByExampleAndRowBounds(example, rowBounds);
    }

    public List<T> selectByRowBounds(T record, RowBounds rowBounds) {
        return getMapper().selectByRowBounds(record, rowBounds);
    }

    public int selectCount(T record) {
        return getMapper().selectCount(record);
    }

    public int selectCountByExample(Example example) {
        return getMapper().selectCountByExample(example);
    }

    public T selectOne(T record) {
        return getMapper().selectOne(record);
    }
}
