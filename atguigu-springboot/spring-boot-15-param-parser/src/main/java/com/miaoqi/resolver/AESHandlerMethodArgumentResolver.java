package com.miaoqi.resolver;

import com.miaoqi.annotation.AESRequestParam;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Component
public class AESHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        System.out.println("判断是否支持AES解密");
        // 获取方法名
        System.out.println(parameter.getMethod().getName());
        // 获取参数名称
        System.out.println(parameter.getParameterName());
        // 判断是否包含注解
        return parameter.hasParameterAnnotation(AESRequestParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        System.out.println("进到了AES参数解析器中");
        // 获取HttpServletRequest
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        // 获取注解信息
        AESRequestParam requestParam = parameter.getParameterAnnotation(AESRequestParam.class);
        // 获取传递过来的值, 如果AESRequestParam指定了value属性按照value属性获取, 否则按照参数名获取
        String parameterValue;
        if (!StringUtils.isEmpty(requestParam.value())) {
            parameterValue = request.getParameter(requestParam.value());
        } else {
            parameterValue = request.getParameter(parameter.getParameterName());
        }

        // 此处模拟RquestParam的require属性
        if (requestParam.required() && Objects.isNull(parameterValue)) {
            throw new IllegalArgumentException("参数不合法");
        }
        // 模拟进行AES解密
        return this.deCrypt(parameterValue);
    }

    private Object deCrypt(String parameterValue) {
        return parameterValue + "jiemi";
    }
}
