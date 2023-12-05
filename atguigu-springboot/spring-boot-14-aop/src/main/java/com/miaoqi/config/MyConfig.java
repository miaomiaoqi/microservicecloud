package com.miaoqi.config;

import com.miaoqi.filter.Filter1;
import com.miaoqi.filter.Filter4;
import com.miaoqi.interceptor.Interceptor1;
import com.miaoqi.interceptor.Interceptor2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer {

    @Autowired
    private Interceptor1 interceptor1;
    @Autowired
    private Interceptor2 interceptor2;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 先加入的先执行, 与 @Order 无关
        registry.addInterceptor(this.interceptor1).addPathPatterns("/**");
        registry.addInterceptor(this.interceptor2).addPathPatterns("/**");
    }

    @Bean
    public FilterRegistrationBean filter1() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new Filter1());
        bean.addUrlPatterns("/hello", "/errorController");
        bean.setOrder(-50);
        return bean;
    }

    @Bean
    public FilterRegistrationBean filter4() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new Filter4());
        bean.addUrlPatterns("/hello", "/errorController");
        bean.setOrder(-100);
        return bean;
    }

}
