package com.miaoqi.springboot.springbootquartz.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Quartz执行任务的线程池配置
 *
 * @author miaoqi
 * @date 2019/1/15
 */
@Configuration
public class QuartzThreadPoolConfiguration {

    @Bean
    ThreadPoolTaskExecutor quartzThreadPoolExecutor() {
        ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
        // 线程池所使用的缓冲队列
        poolTaskExecutor.setQueueCapacity(0);
        // 线程池维护线程的最少数量
        poolTaskExecutor.setCorePoolSize(5);
        // 线程池维护线程的最大数量
        poolTaskExecutor.setMaxPoolSize(1000);
        // 线程池维护线程所允许的空闲时间
        poolTaskExecutor.setKeepAliveSeconds(30000);
        return poolTaskExecutor;
    }
}