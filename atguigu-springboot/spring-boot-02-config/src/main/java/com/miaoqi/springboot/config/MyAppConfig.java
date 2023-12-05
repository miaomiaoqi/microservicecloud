package com.miaoqi.springboot.config;

import com.miaoqi.springboot.service.HelloService;
import org.springframework.context.annotation.Bean;

/**
 * @author miaoqi
 * @date 2018-04-16 下午4:32
 **/
// @Configuration: 指明当前类是一个配置类, 就是来替代之前的Spring配置文件
// @Configuration
public class MyAppConfig {

    @Bean
    public HelloService helloService() {
        System.out.println("配置类@Bean给容器中添加了一个组件...");
        return new HelloService();
    }

}
