package com.fullstack.gateway.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.fullstack.gateway.model.GatewayAccessLogs;

@Mapper
public interface GatewayLogsMapper {

	@Insert("INSERT INTO testdb.gateway_access_logs(access_id,path,params,headers,ip,http_status,method,request_time,response_time,use_time,user_agent,region,server_ip,service_id,error) values(#{access_id},#{path},#{params},#{headers},#{ip},#{http_status},#{method},#{request_time},#{response_time},#{use_time},#{user_agent},#{region},#{server_ip},#{service_id},#{error})")
	@SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = long.class)
	void insertLog(GatewayAccessLogs log);
	

}
