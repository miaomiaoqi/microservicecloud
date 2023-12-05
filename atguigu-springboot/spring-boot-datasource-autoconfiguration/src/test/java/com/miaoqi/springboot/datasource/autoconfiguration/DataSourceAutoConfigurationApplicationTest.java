package com.miaoqi.springboot.datasource.autoconfiguration;

import com.miaoqi.springboot.datasource.autoconfiguration.config.MainConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSourceAutoConfigurationApplicationTest {

    private AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);

    @Test
    public void testImport() {
        printBeans(ctx);
    }

    private void printBeans(AnnotationConfigApplicationContext ctx) {
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
    }

}