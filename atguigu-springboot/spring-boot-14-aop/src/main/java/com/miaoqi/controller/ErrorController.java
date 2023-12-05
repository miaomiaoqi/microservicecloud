package com.miaoqi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/errorController")
public class ErrorController {

    @GetMapping
    public int error(int i) {
        System.out.println("会抛异常的方法");
        return 1 / i;
    }

}
