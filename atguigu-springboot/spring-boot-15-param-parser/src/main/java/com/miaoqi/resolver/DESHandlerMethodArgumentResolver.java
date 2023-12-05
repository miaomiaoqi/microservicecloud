package com.miaoqi.resolver;

import com.miaoqi.annotation.DESRequestParam;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class DESHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        System.out.println("判断是否支持DES解密");
        System.out.println(parameter.getMethod().getName());
        System.out.println(parameter.getParameterName());
        return parameter.hasParameterAnnotation(DESRequestParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        System.out.println("进到了DES参数解析器中");
        return null;
    }
}
