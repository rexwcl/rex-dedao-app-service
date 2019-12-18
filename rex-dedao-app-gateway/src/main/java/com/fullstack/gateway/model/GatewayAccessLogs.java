package com.fullstack.gateway.model;

import java.util.Date;

public class GatewayAccessLogs {
	private Long id;
	private Long access_id;
	private String path;
	private String params;
	private String headers;
	private String ip;
	private String http_status;
	private String method;
	private Date request_time;
	private Date response_time;
	private Long use_time;
	private String user_agent;
	private String region;
	private String server_ip;
	private String service_id;
	private String error;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAccess_id() {
		return access_id;
	}
	public void setAccess_id(Long access_id) {
		this.access_id = access_id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getHeaders() {
		return headers;
	}
	public void setHeaders(String headers) {
		this.headers = headers;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getHttp_status() {
		return http_status;
	}
	public void setHttp_status(String http_status) {
		this.http_status = http_status;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Date getRequest_time() {
		return request_time;
	}
	public void setRequest_time(Date request_time) {
		this.request_time = request_time;
	}
	public Date getResponse_time() {
		return response_time;
	}
	public void setResponse_time(Date response_time) {
		this.response_time = response_time;
	}
	public Long getUse_time() {
		return use_time;
	}
	public void setUse_time(Long use_time) {
		this.use_time = use_time;
	}
	public String getUser_agent() {
		return user_agent;
	}
	public void setUser_agent(String user_agent) {
		this.user_agent = user_agent;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getServer_ip() {
		return server_ip;
	}
	public void setServer_ip(String server_ip) {
		this.server_ip = server_ip;
	}
	public String getService_id() {
		return service_id;
	}
	public void setService_id(String service_id) {
		this.service_id = service_id;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

	
}
