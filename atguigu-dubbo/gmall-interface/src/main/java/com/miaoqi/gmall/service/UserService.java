package com.miaoqi.gmall.service;

import com.miaoqi.gmall.bean.UserAddress;

import java.util.List;

public interface UserService {

    /**
     * 按照用户id返回所有的收获地址
     *
     * @author miaoqi
     * @date 2018/11/29
     * @param userId
     * @return
     */
    public List<UserAddress> getUserAddressList(String userId);

}
