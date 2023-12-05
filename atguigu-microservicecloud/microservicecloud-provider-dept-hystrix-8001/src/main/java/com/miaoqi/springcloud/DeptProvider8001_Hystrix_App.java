package com.miaoqi.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

// @SpringBootApplication
@EnableEurekaClient // Eureka客户端
// @EnableDiscoveryClient // 服务发现
@EnableHystrix // 对Hystrix熔断器的支持
// @EnableCircuitBreaker // 标识开启熔断器
@SpringCloudApplication // 注解等同与同属引入@SpringBootAppliction + @EnableDiscoveryClient + @EnableCircuitBreaker
public class DeptProvider8001_Hystrix_App {

    public static void main(String[] args) {
        SpringApplication.run(DeptProvider8001_Hystrix_App.class, args);
    }

}
