package com.miaoqi.springcloud;

import com.miaoqi.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
//在启动该微服务的时候就能去加载我们的自定义Ribbon配置类，从而使配置生效
// @RibbonClient(name = "MICROSERVICECLOUD-DEPT", configuration = MySelfRule.class)
// configuration所指定的配置类, 不能放在@ComponentScan所扫描的当钱包以及子包下, 否则我们定义的这个
// 配置类就会被所有的Ribbon客户端所共享, 也就达不到特殊化定制的目的了
@RibbonClient(name = "MICROSERVICECLOUD-DEPT", configuration = MySelfRule.class)
public class DeptConsumer80_App {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer80_App.class, args);
    }
}
