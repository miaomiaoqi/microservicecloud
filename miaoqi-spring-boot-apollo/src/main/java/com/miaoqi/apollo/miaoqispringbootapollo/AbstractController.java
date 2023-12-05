package com.miaoqi.apollo.miaoqispringbootapollo;

import org.springframework.beans.factory.annotation.Value;

public abstract class AbstractController {

    @Value("${testField:parentField}")
    public String testField;

}
