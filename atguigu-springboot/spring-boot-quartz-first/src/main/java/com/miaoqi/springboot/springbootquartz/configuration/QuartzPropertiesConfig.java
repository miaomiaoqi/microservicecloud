package com.miaoqi.springboot.springbootquartz.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * quartz配置类
 *
 * @author miaoqi
 * @date 2019/1/14
 */
@Configuration
@ConfigurationProperties(prefix = "org")
public class QuartzPropertiesConfig {

    private Properties quartz = new Properties();

    public Properties getQuartz() {
        return quartz;
    }

    public void setQuartz(Properties quartz) {
        this.quartz = quartz;
    }
}
