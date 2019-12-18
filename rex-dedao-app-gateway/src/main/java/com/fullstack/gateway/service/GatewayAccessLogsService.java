package com.fullstack.gateway.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 网关访问日志
 */

public interface GatewayAccessLogsService {
	
	void  saveLogs(HttpServletRequest request, HttpServletResponse response);

}
