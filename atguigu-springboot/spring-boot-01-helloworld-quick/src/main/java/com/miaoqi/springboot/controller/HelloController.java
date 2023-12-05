package com.miaoqi.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author miaoqi
 * @date 2018-04-16 下午2:14
 **/
// 这个类的所有方法返回的数据直接写给浏览器(如果是对象转为json数据)
// @ResponseBody
// @Controller
// @RestController包括了@ResponseBody和@Controller
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

}
