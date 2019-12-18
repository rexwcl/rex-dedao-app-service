package com.fullstack.gateway.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.fullstack.gateway.model.GatewayRoute;
import com.fullstack.gateway.model.GatewayRouteRegister;



@Mapper
public interface GatewayRouteMapper {
	
	@Select("SELECT * FROM testdb.gateway_route where status =1")
	List<GatewayRoute> queryServiceList();
	
	@Select("SELECT * FROM testdb.gateway_route")
	List<GatewayRoute> queryServicesList();
	
	@Insert("insert into testdb.gateway_route(path,serviceId,serviceName,url,type,routeDesc,owner,org) values(#{path},#{serviceId},#{serviceName},#{url},#{type},#{routeDesc},#{owner},#{org})")
	@SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "routeId", before = false, resultType = int.class)
	void addRoute(GatewayRouteRegister route);
	
	@Delete("delete from testdb.gateway_route where routeId =#{routeId}")
	void removeRoute(@Param("routeId") Integer routeId);
	
	@Update("update testdb.gateway_route set status =#{status} where routeId =#{routeId}")
	void updateRoute(@Param("routeId") Integer routeId,@Param("status") Boolean status );
	
	@Select("SELECT routeId FROM testdb.gateway_route where serviceId=#{serviceId}")
	Integer checkService(@Param("serviceId") String serviceId);
	

}
