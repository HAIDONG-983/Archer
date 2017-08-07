package org.archer.common.utils;

import org.archer.common.mybatis.generator.ArcherMyBatisGenerator;
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
public class MybatisGeneratorUtil {
    public static void generator() throws Exception {
        List<String> warnings = new ArrayList<String>();

        Resource resource = new ClassPathResource("generator.xml");
        File resourceFile = resource.getFile();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        //cp.parseConfiguration(resourceFile);
        Configuration config = cp.parseConfiguration(resourceFile);
        DefaultShellCallback callback = new DefaultShellCallback(true);
        ArcherMyBatisGenerator myBatisGenerator = new ArcherMyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        for (String warning : warnings) {
            System.out.println(warning);
        }
    }
}
