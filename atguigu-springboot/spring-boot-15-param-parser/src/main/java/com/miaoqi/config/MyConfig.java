package com.miaoqi.config;

import com.miaoqi.resolver.AESHandlerMethodArgumentResolver;
import com.miaoqi.resolver.DESHandlerMethodArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class MyConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new AESHandlerMethodArgumentResolver());
        argumentResolvers.add(new DESHandlerMethodArgumentResolver());
        super.addArgumentResolvers(argumentResolvers);
    }
}
