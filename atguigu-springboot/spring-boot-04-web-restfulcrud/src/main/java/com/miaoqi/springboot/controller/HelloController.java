package com.miaoqi.springboot.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miaoqi.springboot.exception.UserNotExistException;

@Controller
public class HelloController {

    //    @RequestMapping({ "/", "/index.html" })
    //    public String index() {
    //        return "login";
    //    }

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(@RequestParam("user") String user) {
        if (user.equals("aaa")) {
            throw new UserNotExistException();
        }
        return "hello world";
    }

    // 查出一些数据, 在页面展示
    @RequestMapping("/success")
    public String success(Map<String, Object> map) {
        // classpath:/templates/success.html
        map.put("hello", "你好");
        return "success";
    }

}
