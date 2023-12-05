package com.miaoqi.logback.request;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestTest {

    /**
     * 通过 getLogger(Class<?> clazz) 获取 logger
     * 会获取到
     * <logger name="com.miaoqi" level="info" additivity="false">
     * <appender-ref ref="REQUEST-APPENDER"/>
     * </logger>
     * 这个 logger, 因为 RequestTest.class 会转变成 com.miaoqi.logback.request.RequestTest 会匹配到 com.miaoqi
     */
    private static final Logger logger = LoggerFactory.getLogger(RequestTest.class);

    // 会输出到 request.log 文件中
    @Test
    public void testRequest() {
        logger.info("request log");
    }

    @Test
    public void testSize() throws InterruptedException {
        for (int i = 0; i < 50; i++) {
            logger.info("request log" + i);
        }
    }

}
