package org.archer.common.mybatis.service;

import org.archer.common.mybatis.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static oracle.net.aso.C11.o;

/**
 * Created by yukunpeng on 2017/8/4.
 */
@Service
public abstract class AbstractBaseService<Entity,Example> implements BaseService<Entity,Example>{

    @Autowired
    protected BaseMapper<Entity,Example> mapper;

    public List<Entity> query(Example example) {
        return mapper.selectByExample(example);
    }


}
