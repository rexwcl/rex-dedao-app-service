package com.fullstack.gateway.config;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.zuul.ZuulFilter;
import com.fullstack.gateway.filter.ZuulErrorFilter;
import com.fullstack.gateway.filter.ZuulResponseFilter;
import com.fullstack.gateway.locator.ZuulRouteLocator;
import com.fullstack.gateway.service.GatewayRouteService;

@Configuration
public class ApiGatewayConfiguration {
    private ZuulRouteLocator zuulRoutesLocator;
    
    /**
     * 响应过滤器
     *
     * @return
     */
    @Bean
    public ZuulFilter zuulResponseFilter() {
        return new ZuulResponseFilter();
    }

    /**
     * 错误过滤器
     *
     * @return
     */
    @Bean
    public ZuulFilter zuulErrorFilter() {
        return new ZuulErrorFilter();
    }


    /**
     * 初始化路由加载器
     *
     * @return
     */
    @Bean
    public ZuulRouteLocator zuulRouteLocator(ZuulProperties zuulProperties, ServerProperties serverProperties, GatewayRouteService gatewayRouteService) {
        zuulRoutesLocator = new ZuulRouteLocator(serverProperties.getServlet().getContextPath(), zuulProperties, gatewayRouteService);
        return zuulRoutesLocator;
    }



}
