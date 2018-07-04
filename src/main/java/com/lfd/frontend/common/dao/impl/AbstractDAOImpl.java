package com.lfd.frontend.common.dao.impl;

import com.lfd.frontend.common.dao.AbstractDAO;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 11/17/2016.
 */
abstract public class AbstractDAOImpl<T, PK extends Serializable> extends BaseDAOImpl<T> implements AbstractDAO<T, PK> {

    public int delete(T record) {
        return getMapper().delete(record);
    }

    public int deleteByPrimaryKey(PK id) {
        return getMapper().deleteByPrimaryKey(id);
    }

    public int deleteByExample(Example example) {
        return getMapper().deleteByExample(example);
    }

    @Override
    public int insert(T t) {
        return getMapper().insert(t);
    }

    @Override
    public int insertSelective(T t) {
        return getMapper().insertSelective(t);
    }

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
