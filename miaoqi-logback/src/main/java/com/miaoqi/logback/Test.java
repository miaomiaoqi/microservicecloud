package com.miaoqi.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {

    /**
     * 这是slf4j的接口，由于我们引入了logback-classic依赖，所以底层实现是logback
     */
    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        logger.info("hello world");
    }
}
