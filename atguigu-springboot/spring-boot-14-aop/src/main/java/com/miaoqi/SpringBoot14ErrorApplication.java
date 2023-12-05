package com.miaoqi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@ServletComponentScan(basePackages = {"com.miaoqi.filter"})
public class SpringBoot14ErrorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot14ErrorApplication.class, args);
    }

}
