package com.fullstack.gateway.rspmodel;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(description = "返回模型")
public class RspModel {
	@ApiModelProperty(notes = "返回码", example = "200", required = true, dataType = "java.lang.Integer")
	@NotNull
	private Integer code;
	
	@ApiModelProperty(notes = "返回信息", example = "成功", required = true, dataType = "java.lang.String")
	@NotNull
	private String message;
	
	@ApiModelProperty(notes = "返回服务接口数据", required = true, dataType = "java.lang.Object")
	private Object data;

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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	

}
