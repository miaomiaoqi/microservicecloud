package com.miaoqi.gmall.config;

import com.alibaba.dubbo.config.*;
import com.miaoqi.gmall.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class MyDubboConfig {

    /**
     * dubbo.application.name=user-service-provider
     * 替代了dubbo的配置文件
     *
     * @author miaoqi
     * @date 2018/12/4
     * @param
     * @return
     */
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("user-service-provider");
        return applicationConfig;
    }

    /**
     * dubbo.registry.address=127.0.0.1:2181
     * 替代了registry
     *
     * @author miaoqi
     * @date 2018/12/4
     * @param
     * @return
     */
    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress("127.0.0.1:2181");
        return registryConfig;
    }

    /**
     * dubbo.protocol.name=dubbo
     * dubbo.protocol.port=20880
     * 配置dubbo通信规则
     *
     * @author miaoqi
     * @date 2018/12/4
     * @param
     * @return
     */
    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(20880);
        return protocolConfig;
    }

    /**
     * <dubbo:service interface="com.miaoqi.gmall.service.UserService"
     *        ref="userServiceImpl01" timeout="1000" version="1.0.0"/>
     * 创建服务
     *
     * @author miaoqi
     * @date 2018/12/4
     * @param
     * @return
     */
    @Bean
    public ServiceConfig<UserService> serviceConfig(UserService userService) {
        ServiceConfig<UserService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface("com.miaoqi.gmall.service.UserService");
        serviceConfig.setRef(userService);
        serviceConfig.setVersion("1.0.0");

        // 配置每一个method的信息
        MethodConfig methodConfig = new MethodConfig();
        methodConfig.setName("getUserAddressList");
        methodConfig.setTimeout(5000);

        // 将method的配置设置到service的配置中
        serviceConfig.setMethods(Collections.singletonList(methodConfig));

        // ProviderConfig
        // MonitorConfig
        return serviceConfig;
    }

}
