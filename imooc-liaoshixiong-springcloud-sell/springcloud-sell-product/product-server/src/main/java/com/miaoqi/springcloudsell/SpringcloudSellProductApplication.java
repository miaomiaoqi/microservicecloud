package com.miaoqi.springcloudsell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringcloudSellProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudSellProductApplication.class, args);
    }

}
