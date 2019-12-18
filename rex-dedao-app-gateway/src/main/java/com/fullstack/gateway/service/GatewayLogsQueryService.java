package com.fullstack.gateway.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.fullstack.gateway.mapper.GatewayLogsQueryMapper;
import com.fullstack.gateway.model.GatewayAccessLogs;

@Service
public class GatewayLogsQueryService {
	
	@Autowired
	private GatewayLogsQueryMapper logsquery;
	
	public PageInfo findAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<GatewayAccessLogs> logsList = logsquery.findAll();
        PageInfo<GatewayAccessLogs> pageInfo = new PageInfo<>(logsList);
        return pageInfo;
    }
	
	public PageInfo findbyServiceId(int pageNum, int pageSize, String service_id) {
        PageHelper.startPage(pageNum, pageSize);
        List<GatewayAccessLogs> logsList = logsquery.findbyServiceId(service_id);
        PageInfo<GatewayAccessLogs> pageInfo = new PageInfo<>(logsList);
        return pageInfo;
    }
	
	public PageInfo findbyPath(int pageNum, int pageSize, String path) {
        PageHelper.startPage(pageNum, pageSize);
        List<GatewayAccessLogs> logsList = logsquery.findbyPath(path);
        PageInfo<GatewayAccessLogs> pageInfo = new PageInfo<>(logsList);
        return pageInfo;
    }
	
	public PageInfo findbyBoth(int pageNum, int pageSize, String path, String service_id) {
        PageHelper.startPage(pageNum, pageSize);
        List<GatewayAccessLogs> logsList = logsquery.findbyBoth(path, service_id);
        PageInfo<GatewayAccessLogs> pageInfo = new PageInfo<>(logsList);
        return pageInfo;
    }
	

}
