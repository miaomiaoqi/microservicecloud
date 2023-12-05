package com.miaoqi.springboot.datasource.autoconfiguration.config;

import com.miaoqi.springboot.datasource.autoconfiguration.bean.AnimalConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
// 使用 Import 加载 bean 对象, 先加载的先生效
@Import({AnimalConfiguration.DogConfiguration.class, AnimalConfiguration.CatConfiguration.class})
public class MainConfig {
}
