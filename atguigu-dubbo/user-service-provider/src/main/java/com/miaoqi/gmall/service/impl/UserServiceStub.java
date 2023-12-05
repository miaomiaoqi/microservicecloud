package com.miaoqi.gmall.service.impl;

import com.miaoqi.gmall.bean.UserAddress;
import com.miaoqi.gmall.service.UserService;

import java.util.List;

public class UserServiceStub implements UserService {
    @Override
    public List<UserAddress> getUserAddressList(String userId) {
        return null;
    }

    // private final UserService userService;
	//
    //
	// /**
	//  * 传入的是userService远程的代理对象
	//  * @param userService
	//  */
	// public UserServiceStub(UserService userService) {
	// 	super();
	// 	this.userService = userService;
	// }
    //
    //
	// @Override
	// public List<UserAddress> getUserAddressList(String userId) {
	// 	// TODO Auto-generated method stub
	// 	System.out.println("UserServiceStub.....");
	// 	if(!StringUtils.isEmpty(userId)) {
	// 		return userService.getUserAddressList(userId);
	// 	}
	// 	return null;
	// }

}