package com.atguigu.distributed.lock.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CuratorConfig {

    @Bean
    public CuratorFramework curatorFramework() {
        // 初始化一个重试策略, 这里使用的是指数补偿策略. 初始间隔时间, 重试次数
        RetryPolicy retry = new ExponentialBackoffRetry(10000, 3);
        // 初始化 curator 客户端
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", retry);
        client.start(); // 手动启动, 否则很懂方法不工作
        return client;
    }

}
