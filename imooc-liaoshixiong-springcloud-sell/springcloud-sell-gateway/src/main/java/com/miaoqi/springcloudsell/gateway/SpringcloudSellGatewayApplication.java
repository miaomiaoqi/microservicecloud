package com.miaoqi.springcloudsell.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableCircuitBreaker
public class SpringcloudSellGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudSellGatewayApplication.class, args);
    }

}
