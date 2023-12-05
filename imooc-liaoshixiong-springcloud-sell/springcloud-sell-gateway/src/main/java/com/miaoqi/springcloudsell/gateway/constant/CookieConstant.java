package com.miaoqi.springcloudsell.gateway.constant;

import java.util.concurrent.TimeUnit;

public class CookieConstant {
    /**
     * 卖家
     */
    public static final String TOKEN = "token";

    /**
     * 买家
     */
    public static final String OPENID = "openid";

    /**
     * 过期时间(单位: s)
     */
    public static final Integer expire = Math.toIntExact(TimeUnit.HOURS.toSeconds(2));
}
