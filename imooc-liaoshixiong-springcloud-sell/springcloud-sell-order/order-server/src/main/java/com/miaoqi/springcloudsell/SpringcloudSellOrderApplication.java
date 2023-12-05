package com.miaoqi.springcloudsell;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
// @SpringBootApplication
// @EnableDiscoveryClient 开启 eureka
// @EnableCircuitBreaker 开启 hystrix
@EnableHystrixDashboard
@SpringCloudApplication
public class SpringcloudSellOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudSellOrderApplication.class, args);
    }

}
