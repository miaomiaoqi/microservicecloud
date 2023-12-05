package com.miaoqi.gmall.service.impl;

import com.miaoqi.gmall.bean.UserAddress;
import com.miaoqi.gmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

public class UserServiceStub implements UserService {

    @Autowired
    private final UserService userService;

    /**
     * 构造方法传输的是远程的代理对象
     *
     * @author miaoqi
     * @date 2018/12/2
     * @param userService
     * @return
     */
    public UserServiceStub(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<UserAddress> getUserAddressList(String userId) {
        System.out.println("UserServiceStub......");
        if (StringUtils.isEmpty(userId)) {
            return userService.getUserAddressList(userId);
        }
        return null;
    }
}
