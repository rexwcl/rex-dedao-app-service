package com.fullstack.gateway.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.fullstack.gateway.model.GatewayAccessLogs;

@Mapper
public interface GatewayLogsQueryMapper {
	
	@Select("SELECT * FROM testdb.gateway_access_logs where http_status !=0")
    List<GatewayAccessLogs> findAll();

	@Select("SELECT * FROM testdb.gateway_access_logs where http_status !=0 and service_id like CONCAT('%',#{service_id},'%')")
    List<GatewayAccessLogs> findbyServiceId(@Param("service_id") String service_id);
	
	
	@Select("SELECT * FROM testdb.gateway_access_logs where http_status !=0 and path like CONCAT('%',#{path},'%')")
    List<GatewayAccessLogs> findbyPath(@Param("path") String path);
	
	@Select("SELECT * FROM testdb.gateway_access_logs where http_status !=0 and path like CONCAT('%',#{path},'%') and service_id like CONCAT('%',#{service_id},'%')")
    List<GatewayAccessLogs> findbyBoth(@Param("path") String path, @Param("service_id") String service_id);
}
