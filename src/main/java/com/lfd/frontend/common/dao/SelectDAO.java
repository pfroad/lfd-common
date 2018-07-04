package com.lfd.frontend.common.dao;

import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 11/17/2016.
 */
public interface SelectDAO<T, PK extends Serializable> {
    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     *
     * @param record
     * @return
     */
    List<T> select(T record);

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param id
     * @return
     */
    T selectByPrimaryKey(PK id);

    /**
     * 查询全部结果
     *
     * @return
     */
    List<T> selectAll();

    /**
     * 根据Example条件进行查询。
     * Example example = new Example(Country.class);
     * example.createCriteria().andGreaterThan("id", 100);
     * countryList = countryDAO.selectByExample(example);
     * @param example
     * @return
     */
    List<T> selectByExample(Example example);

    /**
     * 根据example条件和RowBounds进行分页查询
     *
     * @param example
     * @param rowBounds
     * @return
     */
    List<T> selectByExampleAndRowBounds(Example example, RowBounds rowBounds);

    /**
     * 根据实体属性和RowBounds进行分页查询
     *
     * @param record
     * @param rowBounds
     * @return
     */
    List<T> selectByRowBounds(T record, RowBounds rowBounds);

    /**
     * 根据实体中的属性查询总数，查询条件使用等号
     *
     * @param record
     * @return
     */
    int selectCount(T record);

    /**
     * 根据Example条件进行查询总数
     *
     * @param example
     * @return
     */
    int selectCountByExample(Example example);

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
     *
     * @param record
     * @return
     */
    T selectOne(T record);
}
