package com.lfd.frontend.common.impl;

import com.lfd.frontend.common.AbstractService;
import com.lfd.frontend.common.dao.AbstractDAO;
import com.github.pagehelper.PageHelper;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 10/31/2016.
 */
abstract public class AbstractServiceImpl<T, PK extends Serializable> implements AbstractService<T, PK> {
    @Override
    public T get(PK id) {
        return getAbstractDAO().selectByPrimaryKey(id);
    }

    @Override
    public int insert(T entity) {
        return getAbstractDAO().insert(entity);
    }

    @Override
    public int update(T entity) {
        return getAbstractDAO().updateByPrimaryKey(entity);
    }

    @Override
    public int delete(PK id) {
        return getAbstractDAO().deleteByPrimaryKey(id);
    }

    @Override
    public List<T> selectByExample(Example example) {
        return getAbstractDAO().selectByExample(example);
    }

    @Override
    public List selectPage(int page, int count) {
        PageHelper.startPage(page, count);
        return getAbstractDAO().select(null);
    }

    abstract public AbstractDAO<T, PK> getAbstractDAO();
}
