package org.archer.common.mybatis.service;

import java.util.List;

/**
 * Created by yukunpeng on 2017/8/4.
 */
public interface BaseService<Entity,Example> {

    List<Entity> query(Example example);

    List<Entity> query(Example example,Integer start,Integer limit);

    Long queryCount(Example example);

    int insert(Entity entity);

    void logicDelete(Entity entity,Example example);

    void update(Entity entity);

}
