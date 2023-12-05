package com.miaoqi.springboot.datasource.autoconfiguration.bean;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

public abstract class AnimalConfiguration {

    // 如果在容器中缺少 Animal 或 Animal 的子类的 bean, 该条件就会生效
    @ConditionalOnMissingBean(Animal.class)
    public static class CatConfiguration {
        @Bean
        public Cat cat() {
            return new Cat();
        }
    }

    @ConditionalOnMissingBean(Animal.class)
    public static class DogConfiguration {
        @Bean
        public Dog dog() {
            return new Dog();
        }
    }

}
