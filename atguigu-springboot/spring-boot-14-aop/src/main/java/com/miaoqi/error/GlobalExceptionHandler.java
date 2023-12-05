package com.miaoqi.error;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常捕获处理器
 * 1. 捕获返回json格式
 * 2. 捕获返回页面
 *
 * @author miaoqi
 * @date 2018/9/4
 */
@ControllerAdvice(basePackages = "com.miaoqi.controller")
// 可以扫描 RestCotroller
// @RestControllerAdvice(basePackageClasses = "com.miaoqi.controller")
public class GlobalExceptionHandler implements ResponseBodyAdvice<Object> {

    // @ResponseBody 返回json格式
    // ModelAndView 返回页面
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String, Object> errorResult(RuntimeException e) {
        // 实际开发中, 将异常信息写到日志中(发邮件通知管理者)
        Map<String, Object> errorResultMap = new HashMap<>();
        errorResultMap.put("errorCode", "500");
        errorResultMap.put("errorMsg", "全局捕获异常系统错误: " + e);
        System.out.println("global exception handler");
        return errorResultMap;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        System.out.println("全局响应处理...");
        return body;
    }

}
