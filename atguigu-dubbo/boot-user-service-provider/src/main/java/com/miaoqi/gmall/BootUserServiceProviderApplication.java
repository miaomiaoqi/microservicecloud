package com.miaoqi.gmall;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1. 导入dubbo依赖
 *      1.1 导入dubbo-starter
 *      1.2 导入dubbo其他依赖
 *
 *
 * @author miaoqi
 * @date 2018/11/30
 */
// 开启基于注解的dubbo
@EnableDubbo(scanBasePackages = "com.miaoqi.gmall")
@SpringBootApplication
public class BootUserServiceProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootUserServiceProviderApplication.class, args);
	}
}
