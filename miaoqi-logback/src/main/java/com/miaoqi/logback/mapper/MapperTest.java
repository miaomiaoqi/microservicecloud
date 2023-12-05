package com.miaoqi.logback.mapper;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapperTest {


//    <root level="info">
//        <!-- 定义了两个appender，日志会通过往这两个 appender 的配置输出日志 -->
//        <appender-ref ref="STDOUT-APPENDER"/>
//        <appender-ref ref="ERROROUT-APPENDER"/>
//    </root>

    /**
     * 获取到 <logger name="mapperOut" level="warn"></logger> 这个 logger, 继承 root 中的 appender
     */
    private static final Logger logger = LoggerFactory.getLogger("mapperOut");

    @Test
    public void testInfo() {
        // 不会输出日志, 因为 logger 指定了 level 为 warn, 所以不会输出 info 的日志
        logger.info("mapper info log");
    }

    @Test
    public void testWarn() {
        // 会输出 1 条 warn 日志, 虽然 root 中配置了 2 个 appender, 但是 errorout 指定了 error 等级的日志
        logger.warn("mapper warn log");
    }

    @Test
    public void testError() {
        // 会输出 2 条 error 日志
        logger.error("mapper error log");
    }

}
