package com.fullstack.gateway.model;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(description = "服务注册模型")
public class GatewayRouteRegister {
	
	private Integer routeId;
	@ApiModelProperty(notes = "服务路径", example = "路径表达式(/base/**)", required = true, dataType = "java.lang.String")
	@NotNull
	private String path;
	@ApiModelProperty(notes = "服务ID", example = "rex-dedao-app-xxx-service", required = true, dataType = "java.lang.String")
	@NotNull
	private String serviceId;
	@ApiModelProperty(notes = "服务名", example = "xx服务", required = true, dataType = "java.lang.String")
	@NotNull
	private String serviceName;
	@ApiModelProperty(notes = "服务地址", example = "服务地址", required = true, dataType = "java.lang.String")
	private String url;
	@ApiModelProperty(notes = "服务类型", example = "服务类型", required = true, dataType = "java.lang.String")
	@NotNull
	private String type;
	
	@ApiModelProperty(notes = "服务描述", example = "服务描述", required = false, dataType = "java.lang.String")
	private String routeDesc;
	@ApiModelProperty(notes = "服务所属者", example = "张三", required = true, dataType = "java.lang.String")
	@NotNull
	private String owner;
	@ApiModelProperty(notes = "服务组织", example = "fullstack", required = true, dataType = "java.lang.String")
	@NotNull
	private String org;
	public Integer getRouteId() {
		return routeId;
	}
	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRouteDesc() {
		return routeDesc;
	}
	public void setRouteDesc(String routeDesc) {
		this.routeDesc = routeDesc;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	
	

}
