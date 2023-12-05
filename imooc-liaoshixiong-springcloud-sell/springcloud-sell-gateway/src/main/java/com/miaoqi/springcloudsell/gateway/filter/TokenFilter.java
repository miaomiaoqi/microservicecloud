package com.miaoqi.springcloudsell.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 权限过滤器
 *
 * @author miaoqi
 * @date 2019-06-12
 */
@Component
public class TokenFilter extends ZuulFilter {

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

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        // 这里从 url 参数中, 也可以从 cookie, header 中获取
        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)) {
            // requestContext.setSendZuulResponse(false);
            // requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        return null;
    }
}
