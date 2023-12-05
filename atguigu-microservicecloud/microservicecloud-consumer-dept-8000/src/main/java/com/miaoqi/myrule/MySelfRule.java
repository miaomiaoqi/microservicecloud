package com.miaoqi.myrule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;

@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule() {
        //        return new RandomRule(); // 随机
        //        return new RoundRobinRule(); // 轮询
        return new RandomRule_MQ();
    }

}
