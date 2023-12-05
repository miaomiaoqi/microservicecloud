package com.miaoqi.springboot02config02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// springboot 启动会扫描以下位置的application.properties或者application.yml文件作为Spring boot的默认配置文件
// –file(项目下):./config/
// –file:./
// –classpath:/config/
// –classpath:/
// 优先级由高到底，高优先级的配置会覆盖低优先级的配置;

@SpringBootApplication
public class SpringBoot02Config02Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot02Config02Application.class, args);
    }
}
