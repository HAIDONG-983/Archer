package com.ylink.wfms.sysmng.cfgSysRegistry.controller;

import com.ylink.wfms.sysmng.cfgSysRegistry.entity.CfgSysRegistry;
import com.ylink.wfms.sysmng.cfgSysRegistry.entity.CfgSysRegistryExample;
import com.ylink.wfms.sysmng.cfgSysRegistry.service.CfgSysRegistryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * CfgSysRegistry模块 controller
 * Created by Archer on 2017-08-05 20:54:09.
 */
@Controller
@RequestMapping("CfgSysRegistry")
public class CfgSysRegistryController{

    private static Logger logger = LoggerFactory.getLogger(CfgSysRegistryController.class);

    @Autowired
    private CfgSysRegistryService cfgSysRegistryService;

    @RequestMapping("/getCfgSysRegistryEnum.action")
    @ResponseBody
    public Map getCfgSysRegistryEnum(){
        CfgSysRegistryExample example =new CfgSysRegistryExample();
        List<CfgSysRegistry> list = cfgSysRegistryService.query(example);
        Map<String,String> cfgSysRegistryEnum = new HashMap<String,String>();
        for (CfgSysRegistry cfgSysRegistry:list){
            cfgSysRegistryEnum.put(cfgSysRegistry.getId(),cfgSysRegistry.getName());
        }
        return cfgSysRegistryEnum;
    }
}
