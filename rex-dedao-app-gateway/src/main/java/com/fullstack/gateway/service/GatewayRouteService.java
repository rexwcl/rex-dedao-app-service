package com.fullstack.gateway.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.gateway.mapper.GatewayRouteMapper;
import com.fullstack.gateway.model.GatewayRoute;

@Service
public class GatewayRouteService {
	
	@Autowired
	private GatewayRouteMapper gatewayroutemapper;
	
	public List<GatewayRoute> selectServiceList() {
		return gatewayroutemapper.queryServiceList();
	}
	
	public List<GatewayRoute> selectServicesList() {
		return gatewayroutemapper.queryServicesList();
	}

}
