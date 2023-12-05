package com.miaoqi.springboot.springbootquartz.configuration;

import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.Properties;

@Configuration
@EnableScheduling
public class SchedulerFactoryConfiguration {

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Autowired
    private QuartzPropertiesConfig quartzPropertiesConfig;

    /**
     * 配置任务调度器
     * 使用项目数据源作为quartz数据源
     * @param jobFactory 自定义配置任务工厂
     * @return
     * @throws Exception
     */
    @Bean(destroyMethod = "destroy", autowire = Autowire.NO, name = "beanNameScheduler")
    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory) throws Exception {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        // 将spring管理job自定义工厂交由调度器维护
        schedulerFactoryBean.setJobFactory(jobFactory);
        // 设置覆盖已存在的任务
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        // 项目启动完成后，等待2秒后开始执行调度器初始化
        schedulerFactoryBean.setStartupDelay(2);
        // 设置调度器自动运行
        schedulerFactoryBean.setAutoStartup(true);
        // 设置数据源(使用系统的主数据源，覆盖propertis文件的dataSource配置)
        // schedulerFactoryBean.setDataSource(dataSource);
        // 设置上下文spring bean name
        schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContext");
        // 设置配置文件位置
        // schedulerFactoryBean.setConfigLocation(new ClassPathResource("/quartz.properties"));
        // 将配置文件的内容以properties对象的方式配置
        Properties raw = quartzPropertiesConfig.getQuartz();
        Properties quartzProperties = new Properties();
        ConfigurationProperties annotation = QuartzPropertiesConfig.class.getDeclaredAnnotation(
                ConfigurationProperties.class);
        for (Object key : raw.keySet()) {
            Object value = raw.get(key);
            quartzProperties.setProperty(annotation.prefix() + ".quartz." + key, value.toString());
        }
        schedulerFactoryBean.setQuartzProperties(quartzProperties);
        // 设置线程池
        schedulerFactoryBean.setTaskExecutor(threadPoolTaskExecutor);
        // 设置调度器名称, 手动设置 > bean实例名称 > 配置文件
        schedulerFactoryBean.setSchedulerName("manualScheduler");
        return schedulerFactoryBean;
    }
}