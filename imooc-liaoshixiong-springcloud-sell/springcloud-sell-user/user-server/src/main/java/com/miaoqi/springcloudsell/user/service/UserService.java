package com.miaoqi.springcloudsell.user.service;

import com.miaoqi.springcloudsell.user.pojo.UserInfo;

public interface UserService {

    /**
     * 通过 openid 查询用户信息
     *
     * @author miaoqi
     * @date 2019-06-12
     * @param openid
     * @return
     */
    UserInfo findByOpenid(String openid);

}
