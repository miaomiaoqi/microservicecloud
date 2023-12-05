package com.miaoqi.springcloudsell.gateway.filter;

import com.miaoqi.springcloudsell.gateway.constant.CookieConstant;
import com.miaoqi.springcloudsell.gateway.constant.RedisConstant;
import com.miaoqi.springcloudsell.gateway.utils.CookieUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 权限过滤器(区分买家和卖家)
 *
 * @author miaoqi
 * @date 2019-06-12
 */
@Component
public class AuthFilter extends ZuulFilter {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public String filterType() {
        // FilterConstants
        // public static final String ERROR_TYPE = "error";
        // public static final String POST_TYPE = "post";
        // public static final String PRE_TYPE = "pre";
        // public static final String ROUTE_TYPE = "route";
        // 前置过滤器
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    //  /order/create 只能买家访问(cookie 里有 openid)
    //  /order/finish 只能卖家访问(cookie 里有 token, 并对应的 redis 中的 key)
    //  /product/list 都可以访问

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        if (request.getRequestURI().contains("/order/create")) {
            Cookie cookie = CookieUtil.get(request, CookieConstant.OPENID);
            if (cookie == null || StringUtils.isEmpty(cookie.getValue())) {
                requestContext.setSendZuulResponse(false);
                requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            }
        }
        if (request.getRequestURI().contains("/order/finish")) {
            Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
            if (cookie == null
                    || StringUtils.isEmpty(cookie.getValue())
                    || StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(
                    String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue())))) {
                requestContext.setSendZuulResponse(false);
                requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            }
        }
        return null;
    }
}
