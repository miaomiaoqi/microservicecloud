package com.miaoqi.springcloudsell.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * 跨域配置
 * C - Cross O - Origin R - Resource S - Sharing(跨域资源共享)
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true); // 是否允许 cookie 跨域
        config.setAllowedOrigins(Arrays.asList("*")); // 允许的域名
        config.setAllowedHeaders(Arrays.asList("*")); // 允许的头
        config.setAllowedMethods(Arrays.asList("*")); // 允许的头
        config.setMaxAge(300L);

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
