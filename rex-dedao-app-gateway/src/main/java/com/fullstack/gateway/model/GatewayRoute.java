package com.fullstack.gateway.model;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "服务模型")
public class GatewayRoute {
	
	@ApiModelProperty(notes = "ID", example = "1", required = true, dataType = "java.lang.Integer")
	@NotNull
	private Integer routeId;
	@ApiModelProperty(notes = "服务路径", example = "路径表达式(/base/**)", required = true, dataType = "java.lang.String")
	@NotNull
	private String path;
	@ApiModelProperty(notes = "服务ID", example = "rex-dedao-app-xxx-service", required = true, dataType = "java.lang.String")
	@NotNull
	private String serviceId;
	@ApiModelProperty(notes = "服务名", example = "服务名称", required = true, dataType = "java.lang.String")
	@NotNull
	private String serviceName;
	@ApiModelProperty(notes = "服务路径", example = "http://rexdedao:9000/base", required = false, dataType = "java.lang.String")
	private String url;
	@ApiModelProperty(notes = "服务类型", example = "服务类型", required = false, dataType = "java.lang.String")
	private String type;
	@ApiModelProperty(notes = "服务前缀", example = "false", required = true, dataType = "java.lang.Boolean")
	@NotNull
	private Boolean stripPrefix;
	@ApiModelProperty(notes = "服务重试", example = "true", required = true, dataType = "java.lang.Boolean")
	@NotNull
	private Boolean retryable;
	@ApiModelProperty(notes = "服务状态", example = "true", required = true, dataType = "java.lang.Boolean")
	@NotNull
	private Boolean status;
	@ApiModelProperty(notes = "服务描述", example = "服务描述", required = false, dataType = "java.lang.String")
	private String routeDesc;
	@ApiModelProperty(notes = "服务所属者", example = "张三", required = true, dataType = "java.lang.String")
	@NotNull
	private String owner;
	@ApiModelProperty(notes = "服务ID", example = "rex-dedao-app-xxx-service", required = true, dataType = "java.lang.String")
	@NotNull
	private String org;
	@ApiModelProperty(notes = "服务创建时间", example = "2019-05-14 07:09:19", required = true, dataType = "java.lang.Date")
	@NotNull
	private Date create_time;
	@ApiModelProperty(notes = "服务更新时间", example = "2019-05-14 07:09:19", required = true, dataType = "java.lang.Date")
	@NotNull
	private Date update_time;
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
	public Boolean getStripPrefix() {
		return stripPrefix;
	}
	public void setStripPrefix(Boolean stripPrefix) {
		this.stripPrefix = stripPrefix;
	}
	public Boolean getRetryable() {
		return retryable;
	}
	public void setRetryable(Boolean retryable) {
		this.retryable = retryable;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
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
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
	
	

	

}
