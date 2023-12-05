package com.miaoqi.springcloudsell.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
// @DefaultProperties(defaultFallback = "defaultFallback", commandProperties = @HystrixProperty(name = "", value = ""))
public class HystrixController {

    // 所有配置均可配置到配置文件中

    // @HystrixCommand 配合 @DefaultProperties 会触发默认降级方法

    // 超时配置
    // @HystrixCommand(fallbackMethod = "fallback",
    //         commandProperties = @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value =
    //                 "3000")) // 会指定特殊的降级方法, 优先级高于默认降级, 默认超时 1s, 这个配置会改为 3s

    // 熔断
    // @HystrixCommand(commandProperties = {
    //         @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 设置熔断
    //         @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 默认值20.意思是至少有20个请求才进行 errorThresholdPercentage 错误百分比计算。
    //         @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 半开试探休眠时间，默认值5000ms。当熔断器开启一段时间之后比如5000ms，会尝试放过去一部分流量进行试探，确定依赖服务是否恢复。
    //         @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") // 设定错误百分比，默认值50%，例如一段时间（10s）内有100个请求，其中有55个超时或者异常返回了，那么这段时间内的错误百分比是55%，大于了默认值50%，这种情况下触发熔断器-打开。
    // })

    // @HystrixCommand
    @GetMapping("/getProductInfoList")
    public String getProductInfoList(@RequestParam("number") Integer number) {
        if (number % 2 == 0) {
            return "success";
        }
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://localhost:9916/product/listForOrder",
                Arrays.asList("157875196366160022"),
                String.class);
        // throw new RuntimeException("发送异常了"); 抛异常就会触发降级
    }

    private String fallback() {
        return "太拥挤了, 请稍后再试~";
    }

    private String defaultFallback() {
        return "默认提示: 太拥挤了, 请稍后再试~";
    }

}