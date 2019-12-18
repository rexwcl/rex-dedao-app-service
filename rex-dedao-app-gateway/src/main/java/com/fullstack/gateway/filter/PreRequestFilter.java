package com.fullstack.gateway.filter;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.common.collect.Maps;
import com.fullstack.gateway.gen.SnowflakeIdGenerator;
import com.fullstack.gateway.service.GatewayAccessLogsService;

public class PreRequestFilter extends OncePerRequestFilter {
	private SnowflakeIdGenerator snowflakeIdGenerator;
    private RedisTemplate redisTemplate;
    private GatewayAccessLogsService gatewayAccessLogsService;

    public PreRequestFilter(SnowflakeIdGenerator snowflakeIdGenerator, RedisTemplate redisTemplate, GatewayAccessLogsService gatewayAccessLogsService) {
    	this.snowflakeIdGenerator = snowflakeIdGenerator;
    	this.redisTemplate = redisTemplate;
        this.gatewayAccessLogsService = gatewayAccessLogsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        System.out.println("==> PreFilter");
        Date start = new Date();
//        System.out.println("==> start ["+start.getTime()+ "] path["+httpServletRequest.getRequestURI()+"]");
        try {
            Long requestId = snowflakeIdGenerator.nextId();
            httpServletRequest.setAttribute("pre_request_id", String.valueOf(requestId));
            Map<String, Object> map = Maps.newHashMap();
            map.put("access_id", requestId);
            map.put("request_time", new Date());
            // 3分钟过期
            String key = "pre_request_id:" + requestId;
            // 放入redis缓存
            redisTemplate.opsForValue().set(key, map, 3, TimeUnit.MINUTES);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
        Date end = new Date();
        long usetime = end.getTime() - start.getTime();
//        System.out.println("==> end [" + end.getTime()+"] use ["+ usetime +"] httpStatus=["+httpServletResponse.getStatus() +"]");
        if (httpServletResponse.getStatus() == 200) {
        	if(httpServletRequest.getRequestURI().matches("(.*)/api/v1/(.*)")) {
                gatewayAccessLogsService.saveLogs(httpServletRequest,httpServletResponse);
        	}
        }
    }
}
