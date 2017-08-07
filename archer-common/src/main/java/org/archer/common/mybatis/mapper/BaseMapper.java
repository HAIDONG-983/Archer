package org.archer.common.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * Created by yukunpeng on 2017/8/4.
 */
public interface BaseMapper<Entity,Example> {

    int countByExample(Example example);

    int deleteByExample(Example example);

    int deleteByPrimaryKey(String id);

    int insert(Entity record);

    int insertSelective(Entity record);

    List<Entity> selectByExampleWithRowbounds(Example example, RowBounds rowBounds);

    List<Entity> selectByExample(Example example);

    Entity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Entity record, @Param("example") Example example);

    int updateByExample(@Param("record") Entity record, @Param("example") Example example);

    int updateByPrimaryKeySelective(Entity record);

    int updateByPrimaryKey(Entity record);

}
