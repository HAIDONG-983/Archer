package com.ylink.wfms.sysmng.cfgSysRegistry.service.Impl;

import com.ylink.wfms.sysmng.cfgSysRegistry.service.CfgSysRegistryService;
import com.ylink.wfms.sysmng.cfgSysRegistry.entity.CfgSysRegistry;
import com.ylink.wfms.sysmng.cfgSysRegistry.entity.CfgSysRegistryExample;

import org.archer.common.mybatis.service.AbstractBaseService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CfgSysRegistry模块 Service实现
* Created by Archer on 2017-08-07 09:55:13.
*/
@Service
@Transactional
public class CfgSysRegistryServiceImpl  extends AbstractBaseService<CfgSysRegistry,CfgSysRegistryExample> implements CfgSysRegistryService {

    private static Logger logger = LoggerFactory.getLogger(CfgSysRegistryServiceImpl.class);




}
