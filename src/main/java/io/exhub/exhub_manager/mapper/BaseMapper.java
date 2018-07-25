package io.exhub.exhub_manager.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 基础mapper
 * @author
 * @date 2018/7/25
 */
public interface BaseMapper<Record,Example> {

    /**
     * 添加一条记录
     * @param record
     * @return
     */
    int insert(Record record);

    /**
     * 添加一条记录（部分字段）
     * @param record
     * @return
     */
    int insertSelective(Record record);

    /**
     * 根据主键id查询一条记录
     * @param id
     * @return
     */
    Record selectByPrimaryKey(Long id);

    /**
     * 根据多个字段查询多个记录
     * @param example
     * @return
     */
    List<Record> selectByExample(Example example);

    /**
     * 根据字段查询有多少条记录
     * @param example
     * @return
     */
    long countByExample(Example example);

    /**
     * 根据主键更新一条记录
     * @param record
     * @return
     */
    int updateByPrimaryKey(Record record);

    /**
     * 根据主键更新部分字段
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Record record);


    /**
     * 条件更新记录
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(@Param("record") Record record, @Param("example") Example example);

    /**
     * 更新记录
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") Record record, @Param("example") Example example);

    /**
     * 根据主键id删除一条记录
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 根据部分字段删除记录
     * @param example
     * @return
     */
    int deleteByExample(Example example);
}
