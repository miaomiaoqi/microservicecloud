package com.miaoqi.springcloudsell.user.controller;

import com.miaoqi.springcloudsell.user.constant.CookieConstant;
import com.miaoqi.springcloudsell.user.constant.RedisConstant;
import com.miaoqi.springcloudsell.user.enums.ResultEnum;
import com.miaoqi.springcloudsell.user.enums.RoleEnum;
import com.miaoqi.springcloudsell.user.pojo.UserInfo;
import com.miaoqi.springcloudsell.user.service.UserService;
import com.miaoqi.springcloudsell.user.utils.CookieUtil;
import com.miaoqi.springcloudsell.user.utils.ResultVOUtil;
import com.miaoqi.springcloudsell.user.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 买家登录
     *
     * 参数 openid=abc
     * 登录成功的 cookie 里设置 openid=abc
     *
     * @author miaoqi
     * @date 2019-06-12
     * @param openid
     * @param response
     * @return
     */
    @GetMapping("/buyer")
    public ResultVO buyer(@RequestParam("openid") String openid,
            HttpServletResponse response) {
        // 1. openid和数据库里的数据匹配
        UserInfo userInfo = this.userService.findByOpenid(openid);
        if (Objects.isNull(userInfo)) {
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }
        // 2. 判断角色
        if (!userInfo.getRole().equals(RoleEnum.BUYER.getCode())) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }

        // 3. cookie 里设置 openid=abc
        CookieUtil.set(response, CookieConstant.OPENID, openid, CookieConstant.expire);
        return ResultVOUtil.success();
    }

    /**
     * 卖家登录
     * 参数 token=xyz
     * 登录成功 redis设置 token_uuid=openid, cookie 里设置 token=token_uuid
     *
     * @author miaoqi
     * @date 2019-06-12
     * @param openid
     * @param response
     * @return
     */
    @GetMapping("/seller")
    public ResultVO seller(@RequestParam("openid") String openid,
            HttpServletRequest request,
            HttpServletResponse response) {

        // 判断用户是否已经登录
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null && !StringUtils.isEmpty(
                stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE,
                        cookie.getValue())))) {
            return ResultVOUtil.success();
        }

        // 1. openid和数据库里的数据匹配
        UserInfo userInfo = this.userService.findByOpenid(openid);
        if (Objects.isNull(userInfo)) {
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }
        // 2. 判断角色
        if (!userInfo.getRole().equals(RoleEnum.SELLER.getCode())) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }

        Integer expire = CookieConstant.expire;

        // 3. redis 设置 key=UUID, value=xyz
        String token = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE, token), openid, expire,
                TimeUnit.SECONDS);

        // 4. cookie 里设置 token=UUID
        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.expire);
        return ResultVOUtil.success();
    }

}
