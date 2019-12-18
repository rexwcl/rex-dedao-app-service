package com.fullstack.gateway.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.fullstack.gateway.locator.ZuulRouteLocator;
import com.fullstack.gateway.rspmodel.RspModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@RequestMapping("/api/v1/")
@Api(description = "网关服务开放接口")
@RestController
public class GatewayController {

	@Value("${spring.application.name}")
	private String serviceId;

	@Autowired
	private ZuulRouteLocator zuulRoutesLocator;

	@ApiIgnore
	@RequestMapping(value = "/service/list", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "获取服务列表")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 400, message = "错误的请求"),
			@ApiResponse(code = 401, message = "没有权限查看此资源"), @ApiResponse(code = 404, message = "资源不存在"),
			@ApiResponse(code = 500, message = "内部错误请联系管理员") })
	public ResponseEntity<RspModel> getServiceList() {

		List<Map> services = Lists.newArrayList();
		Map gateway = Maps.newHashMap();
		gateway.put("serviceId", serviceId);
		gateway.put("serviceName", "API网关");
		services.add(gateway);

		List<Route> routes = zuulRoutesLocator.getRoutes();
		if (routes != null && routes.size() > 0) {
			routes.forEach(route -> {

				String name = route.getId();

				Map service = Maps.newHashMap();
				service.put("serviceId", route.getId());
				service.put("serviceName", name);
				services.add(service);

			});
		}

		RspModel rsp = new RspModel();
		rsp.setCode(200);
		rsp.setMessage("成功");
		rsp.setData(services);

		return new ResponseEntity<RspModel>(rsp, HttpStatus.OK);

	}

}
