package com.ylink.wfms.webservice;

import javax.jws.WebService;

/**
 * Created by yukunpeng on 2017/7/21.
 */
public class DemoWebServiceImpl implements DemoWebService{

    public String demo() {
        return "hello world";
    }
}
