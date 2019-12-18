package com.fullstack.gateway.rspmodel;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fullstack.gateway.model.GatewayRoute;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(description = "用户查询返回模型")
public class RouteRspModel {
	
	@ApiModelProperty(notes = "返回码", example = "200", required = true, dataType = "java.lang.Integer")
	@NotNull
	private Integer code;
	
	@ApiModelProperty(notes = "返回信息", example = "成功", required = true, dataType = "java.lang.String")
	@NotNull
	private String message;
	
	@ApiModelProperty(notes = "返回用户数据", required = true, dataType = "java.lang.Object")
	private List<GatewayRoute> data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<GatewayRoute> getData() {
		return data;
	}

	public void setData(List<GatewayRoute> data) {
		this.data = data;
	}
	
	

}
