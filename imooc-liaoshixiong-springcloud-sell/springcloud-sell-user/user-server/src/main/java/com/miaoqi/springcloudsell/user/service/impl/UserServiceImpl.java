package com.miaoqi.springcloudsell.user.service.impl;

import com.miaoqi.springcloudsell.user.pojo.UserInfo;
import com.miaoqi.springcloudsell.user.repository.UserInfoRepository;
import com.miaoqi.springcloudsell.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepository repository;

    @Override
    public UserInfo findByOpenid(String openid) {
        return this.repository.findByOpenid(openid);
    }
}
