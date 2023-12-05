package com.miaoqi.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Aspect
@Component
@Order(1)
public class WebLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("execution(public * com.miaoqi.controller.*.*(..))")
    public void webLog() {
    }

    /**
     * 使用 AOP 前置通知拦截请求参数信息, 在方法执行开始之前
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容 记录 最多半年数据迁移 云备份 nosql 数据库
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("URL: " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD: " + request.getMethod());
        logger.info("IP: " + request.getRemoteAddr());
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            logger.info("name = [{}], value = [{}]", name, request.getParameter(name));
        }
        // 传统写在磁盘上有很大缺点： 分布式情况 服务器集群呢？ 20台服务器，
    }

    /**
     * 在方法执行之后
     *
     * @author miaoqi
     * @date 2020-01-07
     *
     * @param joinPoint
     *
     * @return
     */
    @After("webLog()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println("方法结束..." + joinPoint.getSignature().getName());
    }

    /**
     * 当方法进行返回的时候，returning 属性是指定方法参数中的 ret 来接收返回参数，这样就可以修改返回参数
     *
     * @author miaoqi
     * @date 2020-01-07
     *
     * @param ret
     *
     * @return
     */
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
    }

    /**
     * 当方法执行异常的时候，throwding 是指定方法参数中的 e 来接收异常参数，可以查看发生的什么异常
     *
     * @author miaoqi
     * @date 2020-01-07
     *
     * @param joinPoint
     * @param e
     *
     * @return
     */
    @AfterThrowing(value = "webLog()", throwing = "e")
    public void logException(JoinPoint joinPoint, Exception e) {
        System.out.println("异常... 异常信息：{" + e + "}");
    }

    /**
     * 环绕通知, 可以控制方法的执行
     *
     * @author miaoqi
     * @date 2020-01-07
     *
     * @param joinPoint
     *
     * @return
     */
    @Around("webLog()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //原方法执行之前会打印这个日志
        System.out.println("环绕通知...  开始");
        //执行原方法
        Object obj = joinPoint.proceed();
        //原方法执行结束，打印这行日志
        System.out.println("环绕通知...  结束");
        //返回方法返回参数
        return obj;
    }

}
