package com.miaoqi.springcloudsell.user.repository;

import com.miaoqi.springcloudsell.user.pojo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    UserInfo findByOpenid(String openid);

}
