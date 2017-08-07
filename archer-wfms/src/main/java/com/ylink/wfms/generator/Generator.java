package com.ylink.wfms.generator;

import org.archer.common.utils.MybatisGeneratorUtil;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yukunpeng on 2017/8/2.
 */
public class Generator {


    public static void main(String[] args) throws Exception {
        MybatisGeneratorUtil.generator();

    }

}
