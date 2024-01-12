package com.atguigu.distributed.lock.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        SingleServerConfig serverConfig = config.useSingleServer();
        serverConfig.setAddress("redis://127.0.0.1:6379"); // redis 服务器地址
        serverConfig.setPassword("miaomiaoqi");
        serverConfig.setDatabase(0); // 指定 redis 数据库编号
        // serverConfig.setConnectionMinimumIdleSize(10); // 连接池最小空闲线程数
        // serverConfig.setConnectionPoolSize(50); // 连接池最大线程数
        // serverConfig.setIdleConnectionTimeout(60000); // 线程超时时间
        // serverConfig.setConnectTimeout(); // 客户端获取 redis 连接的超时时间
        // serverConfig.setTimeout(); // 响应超时时间
        return Redisson.create(config);
    }

}
