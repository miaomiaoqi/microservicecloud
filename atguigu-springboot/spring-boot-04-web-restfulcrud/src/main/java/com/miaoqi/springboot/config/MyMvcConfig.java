package com.miaoqi.springboot.config;

import com.miaoqi.springboot.component.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

// 使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //        super.addViewControllers(registry);
        // 浏览器发送 /miaoqi请求来到 success
        registry.addViewController("/miaoqi").setViewName("success");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    // 所有的WebMvcConfigurerAdapter组件都会一起起作用
    // @Bean
    // public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
    //
    // }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/index.html")
                .excludePathPatterns("/").excludePathPatterns("/user/login").excludePathPatterns("/hello")
                .excludePathPatterns("/error/**");
    }

}
