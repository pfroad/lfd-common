package com.lfd.frontend.common;

import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 10/31/2016.
 */
public interface AbstractService<T, PK extends Serializable> {
    T get(PK id);

    int insert(T t);

    int update(T t);

    int delete(PK id);

    List<T> selectByExample(Example example);

    List<T> selectPage(int page, int count);
}
