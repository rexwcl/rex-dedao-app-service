package com.fullstack.gateway.rspmodel;

import javax.validation.constraints.NotNull;

import com.github.pagehelper.PageInfo;

import io.swagger.annotations.ApiModelProperty;

public class RspPageInfoModel {
	
	@ApiModelProperty(notes = "返回码", example = "200", required = true, dataType = "java.lang.Integer")
	@NotNull
	private Integer code;
	
	@ApiModelProperty(notes = "返回信息", example = "成功", required = true, dataType = "java.lang.String")
	@NotNull
	private String message;
	
	@ApiModelProperty(notes = "返回数据", required = true, dataType = "java.lang.Object")
	private PageInfo data;

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

	public PageInfo getData() {
		return data;
	}

	public void setData(PageInfo data) {
		this.data = data;
	}

	
	
}
