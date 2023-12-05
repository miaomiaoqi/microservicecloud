package com.miaoqi.springcloudsell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringcloudSellEurekaApplication01 {
    public static void main(String[] args) {
        SpringApplication.run(SpringcloudSellEurekaApplication01.class, args);
    }
}
