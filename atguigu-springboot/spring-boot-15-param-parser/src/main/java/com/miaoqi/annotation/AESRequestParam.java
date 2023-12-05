package com.miaoqi.annotation;

import java.lang.annotation.*;

/**
 * AES解析注解
 *
 * @author miaoqi
 * @date 2018/10/16
 */
@Target(value = ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AESRequestParam {

    String value() default "";

    boolean required() default false;

}
