package com.fullstack.gateway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.gateway.locator.ZuulRouteLocator;
import com.fullstack.gateway.mapper.GatewayRouteMapper;
import com.fullstack.gateway.model.GatewayRoute;
import com.fullstack.gateway.model.GatewayRouteRegister;
import com.fullstack.gateway.rspmodel.RouteRspModel;
import com.fullstack.gateway.rspmodel.RspModel;
import com.fullstack.gateway.service.GatewayRouteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping("/api/v1/")
@Api(description = "网关智能路由")
@RestController
public class GatewayRouteController {

	@Autowired
	private ZuulRouteLocator zuulRoutesLocator;

	@Autowired
	private GatewayRouteService gatewayRouteService;

	@Autowired
	private GatewayRouteMapper gatewayroutemapper;

	@RequestMapping(value = "/service/all", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "获取服务列表")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 400, message = "错误的请求"),
			@ApiResponse(code = 401, message = "没有权限查看此资源"), @ApiResponse(code = 404, message = "资源不存在"),
			@ApiResponse(code = 500, message = "内部错误请联系管理员") })
	public ResponseEntity<RouteRspModel> getServiceList() {

		List<GatewayRoute> gatewayroutes = gatewayRouteService.selectServicesList();

		RouteRspModel rsp = new RouteRspModel();
		rsp.setCode(200);
		rsp.setMessage("成功");
		rsp.setData(gatewayroutes);

		return new ResponseEntity<RouteRspModel>(rsp, HttpStatus.OK);

	}

	@RequestMapping(value = "/service/add", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "服务接口注册")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "注册成功"), @ApiResponse(code = 400, message = "错误的请求"),
			@ApiResponse(code = 401, message = "没有权限查看此资源"), @ApiResponse(code = 404, message = "资源不存在"),
			@ApiResponse(code = 500, message = "内部错误请联系管理员") })
	public ResponseEntity<RspModel> addRoute(
			@ApiParam(name = "body", required = true) @RequestBody GatewayRouteRegister route) {

		try {
			Integer checkservice = gatewayroutemapper.checkService(route.getServiceId());
			if (checkservice != null) {
				RspModel rsp = new RspModel();
				rsp.setCode(204);
				rsp.setMessage("服务接口存在");
				return new ResponseEntity<RspModel>(rsp, HttpStatus.OK);

			} else {
				gatewayroutemapper.addRoute(route);

				Integer route_id = route.getRouteId();

				if (route_id > 1) {

					zuulRoutesLocator.doRefresh();

					RspModel rsp = new RspModel();
					rsp.setCode(200);
					rsp.setMessage("注册成功");
					return new ResponseEntity<RspModel>(rsp, HttpStatus.OK);
				} else {
					RspModel rsp = new RspModel();
					rsp.setCode(204);
					rsp.setMessage("注册失败");
					return new ResponseEntity<RspModel>(rsp, HttpStatus.NO_CONTENT);
				}
			}

		} catch (Exception ex) {
			RspModel rsp = new RspModel();
			rsp.setCode(500);
			rsp.setMessage(ex.getMessage());
			return new ResponseEntity<RspModel>(rsp, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@RequestMapping(value = "/service/remove", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "服务接口移除")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "注册成功"), @ApiResponse(code = 400, message = "错误的请求"),
			@ApiResponse(code = 401, message = "没有权限查看此资源"), @ApiResponse(code = 404, message = "资源不存在"),
			@ApiResponse(code = 500, message = "内部错误请联系管理员") })
	public ResponseEntity<RspModel> removeRoute(@ApiParam(name = "body", required = true) @RequestBody GatewayRoute route) {

		try {

			gatewayroutemapper.removeRoute(route.getRouteId());

			zuulRoutesLocator.doRefresh();

			RspModel rsp = new RspModel();
			rsp.setCode(200);
			rsp.setMessage("移除成功");
			return new ResponseEntity<RspModel>(rsp, HttpStatus.OK);

		} catch (Exception ex) {
			RspModel rsp = new RspModel();
			rsp.setCode(500);
			rsp.setMessage(ex.getMessage());
			return new ResponseEntity<RspModel>(rsp, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@RequestMapping(value = "/service/operate", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "服务接口操作")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "注册成功"), @ApiResponse(code = 400, message = "错误的请求"),
			@ApiResponse(code = 401, message = "没有权限查看此资源"), @ApiResponse(code = 404, message = "资源不存在"),
			@ApiResponse(code = 500, message = "内部错误请联系管理员") })
	public ResponseEntity<RspModel> operateRoute(@ApiParam(name = "body", required = true) @RequestBody GatewayRoute route) {

		try {

			if (route.getStatus()) {
				gatewayroutemapper.updateRoute(route.getRouteId(), route.getStatus());
			} else {
				gatewayroutemapper.updateRoute(route.getRouteId(), route.getStatus());
			}

			zuulRoutesLocator.doRefresh();

			RspModel rsp = new RspModel();
			rsp.setCode(200);
			rsp.setMessage("操作成功");
			return new ResponseEntity<RspModel>(rsp, HttpStatus.OK);

		} catch (Exception ex) {
			RspModel rsp = new RspModel();
			rsp.setCode(500);
			rsp.setMessage(ex.getMessage());
			return new ResponseEntity<RspModel>(rsp, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
}
