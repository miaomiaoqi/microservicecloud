package com.miaoqi.gmall.service;

import com.miaoqi.gmall.bean.UserAddress;

import java.util.List;

public interface OrderService {

    /**
     * 初始化订单
     *
     * @author miaoqi
     * @date 2018/11/29
     * @param userId
     * @return
     */
    public List<UserAddress> initOrder(String userId);

}
