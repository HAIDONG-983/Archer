package org.archer.common.mybatis.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.RowBounds;
import org.archer.common.mybatis.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static oracle.net.aso.C11.o;
import static org.activiti.engine.impl.util.json.XMLTokener.entity;

/**
 * Created by yukunpeng on 2017/8/4.
 */
@Service
public abstract class AbstractBaseService<Entity,Example> implements BaseService<Entity,Example>{

    @Autowired
    protected BaseMapper<Entity,Example> mapper;


    public List<Entity> query(Example example){
        return mapper.selectByExample(example);
    }

    public List<Entity> query(Example example,Integer start,Integer limit) {
        Page<?> page = PageHelper.startPage(start+1,limit);
        List<Entity> list = query(example);
        return list;
    }


    public Long queryCount(Example example){
        long count=(long)mapper.countByExample(example);
        return count;
    }


    public int insert(Entity entity) {
        return mapper.insert(entity);
    }


    public void logicDelete(Entity entity,Example example){
         mapper.updateByExample(entity,example);
    }


    public void update(Entity entity){
        mapper.updateByPrimaryKey(entity);
    }



}
