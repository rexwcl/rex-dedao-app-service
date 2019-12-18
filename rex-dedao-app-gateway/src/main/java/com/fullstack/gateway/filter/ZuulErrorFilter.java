package com.fullstack.gateway.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.fullstack.gateway.service.GatewayAccessLogsService;

/**
 * zuul错误响应过滤器
 *
 * @author wangclwh
 */

public class ZuulErrorFilter extends ZuulFilter {
    @Autowired
    private GatewayAccessLogsService gatewayAccessLogsService;

    @Override
    public String filterType() {
        return FilterConstants.ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_ERROR_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        // 代理错误日志记录
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        gatewayAccessLogsService.saveLogs(request, response);
        return null;
    }
}

