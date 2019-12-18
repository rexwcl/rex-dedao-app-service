package com.fullstack.gateway.service.impl;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fullstack.gateway.service.GatewayAccessLogsService;
import com.fullstack.gateway.utils.WebUtils;

@Service
public class GatewayAccessLogsServiceImpl implements GatewayAccessLogsService {
	
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${spring.application.name}")
    private String defaultServiceId;

    

	@Override
	public void saveLogs(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
        try {
            int httpStatus = response.getStatus();
            String requestPath = request.getRequestURI();
            String method = request.getMethod();
            Map headers = WebUtils.getHttpHeaders(request);
            Map data = WebUtils.getParameterMap(request);
            Object serviceId = request.getAttribute(FilterConstants.SERVICE_ID_KEY);
            String ip = WebUtils.getIpAddr(request);
            String userAgent = request.getHeader(HttpHeaders.USER_AGENT);
            String serverIp = WebUtils.getLocalIpAddress();
            Object object = request.getAttribute("pre_request_id");
            Object error = request.getAttribute("x.servlet.exception.message");
            if (object != null) {
                String requestId = object.toString();
                String key = "pre_request_id:" + requestId;
                Object cache = redisTemplate.opsForValue().get(key);
                if (cache != null) {
                    Map<String, Object> map = (Map) cache;
                    map.put("access_id", requestId);
                    map.put("path", requestPath);
                    map.put("params", JSONObject.toJSON(data));
                    map.put("headers", JSONObject.toJSON(headers));
                    map.put("ip", ip);
                    map.put("http_status", httpStatus);
                    map.put("method", method);
                    map.put("response_time", new Date());
                    map.put("user_agent", userAgent);
                    map.put("server_ip", serverIp);
                    map.put("service_id", serviceId==null?defaultServiceId:serviceId);
                    map.put("error",error);
                    redisTemplate.delete(key);
                    amqpTemplate.convertAndSend("rexdedao.access.logs", map);
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


	}

}
