package com.miaoqi.gmall.service.impl;

import com.miaoqi.gmall.bean.UserAddress;
import com.miaoqi.gmall.service.OrderService;
import com.miaoqi.gmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 1. 将服务提供者注册到注册中心(暴露服务)
 *      1.1 导入dubbo依赖(2.6.2)
 *      1.2 配置服务提供者
 *
 * 2. 让服务消费者去注册中心订阅服务提供者的地址
 *
 * @author miaoqi
 * @date 2018/11/29
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    UserService userService;

    @Override
    public List<UserAddress> initOrder(String userId) {
        System.out.println("用户id: " + userId);
        // 1. 查询用户收货地址
        List<UserAddress> userAddressList = userService.getUserAddressList(userId);
        for (UserAddress userAddress : userAddressList) {
            System.out.println(userAddress.getUserAddress());
        }
        return userAddressList;
    }
}
