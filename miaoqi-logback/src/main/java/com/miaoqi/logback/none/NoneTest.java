package com.miaoqi.logback.none;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoneTest {

//    <root level="info">
//        <!-- 定义了两个appender，日志会通过往这两个 appender 的配置输出日志 -->
//        <appender-ref ref="STDOUT-APPENDER"/>
//        <appender-ref ref="ERROROUT-APPENDER"/>
//    </root>

    /**
     * 如果没有找到对应的 logger 就用 root 节点这个 logger, level 会生效
     */
    private static final Logger rootLogger = LoggerFactory.getLogger("asfasf");

    /**
     * 获取到 <logger name="noneOut" level="info" additivity="false"/> 这个 logger, 不会继承 root 中的 appender
     * 这个 logger 本身又没有配置 appender, 所以不会向任何地方输出日志
     */
    private static final Logger noneLogger = LoggerFactory.getLogger("noneOut");

    @Test
    public void testRootDebug() {
        // 不会打印日志, 因为 root 节点的 level 是 info, 不会打印 info 以下的日志
        rootLogger.debug("root debug");
    }

    @Test
    public void testRootInfo() {
        // 会打印 1 条日志, 因为 ERROROUT-APPENDER 限定了只输出 ERROR 以上的日志
        rootLogger.info("root info");
    }

    @Test
    public void testRootError() {
        // 会打印 2 条日志, 因为两个 APPENDER 的条件都满足
        rootLogger.error("root error");
    }

    @Test
    public void testNoneInfo() {
        // 不会打印任何日志信息, 因为 noneOut 不继承 root, 自己也没配置 APPENDER
        noneLogger.info("none info");
    }

}
