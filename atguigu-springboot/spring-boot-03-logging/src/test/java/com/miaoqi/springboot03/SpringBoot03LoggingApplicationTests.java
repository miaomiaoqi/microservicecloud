package com.miaoqi.springboot03;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot03LoggingApplicationTests {

    // 记录器
    private Logger logger = LoggerFactory.getLogger(SpringBoot03LoggingApplicationTests.class);

    @Test
    public void contextLoads() {
        //	    System.out.println();
        // 日志级别
        // 由低到高
        // 可以调整输出的日志级别, 日志就只会在这个级别和以后的高级别生效
        logger.trace("这是trace日志...");
        logger.debug("这是debug日志...");
        // SpringBoot默认使用的是info级别的, 没有指定级别就用SpringBoot的默认规定的级别, root级别
        logger.info("这是info日志...");
        logger.warn("这是warn日志...");
        logger.error("这是error日志...");
    }

}
