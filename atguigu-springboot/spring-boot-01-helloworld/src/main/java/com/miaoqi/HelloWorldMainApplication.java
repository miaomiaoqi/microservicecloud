package com.miaoqi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @SpringBootApplication来标注一个主程序类, 说明这是一个Spring Boot应用
 *
 * @author miaoqi
 * @date 2018-04-04 上午11:15
 */
@SpringBootApplication
public class HelloWorldMainApplication {
    public static void main(String[] args) {
        // Spring应用启动起来
        SpringApplication.run(HelloWorldMainApplication.class, args);
    }
}
