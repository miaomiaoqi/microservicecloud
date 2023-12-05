package com.miaoqi.springcloudsell.gateway.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.miaoqi.springcloudsell.gateway.exception.RateLimitException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * 限流拦截器
 *
 * @author miaoqi
 * @date 2019-06-12
 */
public class RateLimitFilter extends ZuulFilter {

    /**
     * google guava 中封装好的令牌限流桶, 每秒放入 100 个
     */
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(100);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 优先级要最高
     */
    @Override
    public int filterOrder() {
        return FilterConstants.SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // 尝试获取一个令牌
        if (!RATE_LIMITER.tryAcquire()) {
            // 如果令牌桶中的令牌不够, 就直接返回错误状态, 或者抛异常
            // requestContext.setSendZuulResponse(false);
            // requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            throw new RateLimitException();
        }
        return null;
    }
}
