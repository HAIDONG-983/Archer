package org.archer.common.mybatis.service;

import java.util.List;

/**
 * Created by yukunpeng on 2017/8/4.
 */
public interface BaseService<Entity,Example> {

    public List<Entity> query(Example example);
}
