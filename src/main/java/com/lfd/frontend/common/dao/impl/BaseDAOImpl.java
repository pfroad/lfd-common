package com.lfd.frontend.common.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by Administrator on 11/17/2016.
 */
abstract public class BaseDAOImpl<T> {
    abstract public Mapper<T> getMapper();
}
