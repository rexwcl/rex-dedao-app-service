package com.fullstack.gateway.locator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.util.StringUtils;

import com.google.common.collect.Maps;
import com.fullstack.gateway.model.GatewayRoute;
import com.fullstack.gateway.service.GatewayRouteService;

public class ZuulRouteLocator extends SimpleRouteLocator {

	private ZuulProperties properties;
	private List<GatewayRoute> routeList;
	private GatewayRouteService gatewayRouteService;

	@Autowired
	public ZuulRouteLocator(String servletPath, ZuulProperties properties, GatewayRouteService gatewayRouteService) {
		super(servletPath, properties);
		this.properties = properties;
		this.gatewayRouteService = gatewayRouteService;
	}

	/**
	 * 加载数据库路由配置
	 *
	 * @return
	 */
	@Override
	protected Map<String, ZuulProperties.ZuulRoute> locateRoutes() {
		LinkedHashMap<String, ZuulProperties.ZuulRoute> routesMap = Maps.newLinkedHashMap();
		routesMap.putAll(super.locateRoutes());
		// 从db中加载路由信息
		routesMap.putAll(loadRouteWithDb());
		// 优化一下配置
		LinkedHashMap<String, ZuulProperties.ZuulRoute> values = Maps.newLinkedHashMap();
		for (Map.Entry<String, ZuulProperties.ZuulRoute> entry : routesMap.entrySet()) {
			String path = entry.getKey();
			// Prepend with slash if not already present.
			if (!path.startsWith("/")) {
				path = "/" + path;
			}
			if (StringUtils.hasText(this.properties.getPrefix())) {
				path = this.properties.getPrefix() + path;
				if (!path.startsWith("/")) {
					path = "/" + path;
				}
			}
			values.put(path, entry.getValue());
		}
		return values;
	}

	@Override
	public void doRefresh() {
		super.doRefresh();
	}

	/**
	 * @return
	 * @description 加载路由配置，由子类去实现
	 */
	public Map<String, ZuulRoute> loadRouteWithDb() {
		Map<String, ZuulProperties.ZuulRoute> routes = Maps.newLinkedHashMap();
		try {
			routeList = gatewayRouteService.selectServiceList();
			if (routeList != null && routeList.size() > 0) {
				for (GatewayRoute result : routeList) {
					if (StringUtils.isEmpty(result.getPath())) {
						continue;
					}
					if (StringUtils.isEmpty(result.getServiceId()) && StringUtils.isEmpty(result.getUrl())) {
						continue;
					}
					ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();

					BeanUtils.copyProperties(result, zuulRoute);
					zuulRoute.setId(result.getServiceId());
					routes.put(zuulRoute.getPath(), zuulRoute);
				}
			}
		} catch (Exception e) {
			System.out.println("加载动态路由错误:" + e.getMessage());
		}
		return routes;
	}

	public List<GatewayRoute> getRouteList() {
		return routeList;
	}

	public void setRouteList(List<GatewayRoute> routeList) {
		this.routeList = routeList;
	}
}
