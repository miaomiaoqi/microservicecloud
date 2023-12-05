package com.miaoqi.controller;

import com.miaoqi.annotation.AESRequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/param")
public class ParamParserController {

    @RequestMapping("/test")
    public String testParamParser(@AESRequestParam(value = "param", required = true) String param1, String param2) {
        System.out.println("------------------");
        System.out.println(param1);
        System.out.println(param2);
        return "success";
    }

}
