package com.ylink.wfms.web;

import com.ylink.wfms.service.WfmsService;
import org.activiti.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yukunpeng on 2017/7/13.
 */
@Component
public abstract class BaseHandler {


    @Autowired
    protected WfmsService wfmsService;

    @Autowired
    protected RepositoryService repositoryService;
}
