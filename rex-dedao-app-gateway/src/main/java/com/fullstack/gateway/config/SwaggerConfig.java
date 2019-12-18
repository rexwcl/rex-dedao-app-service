package com.fullstack.gateway.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.google.common.collect.Lists;
import com.fullstack.gateway.locator.ZuulRouteLocator;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
@Primary // 多个bean时 此类优先使用
public class SwaggerConfig implements SwaggerResourcesProvider {

	// 是否开启swagger，正式环境一般是需要关闭的，可根据springboot的多环境配置进行设置
	@Value(value = "${swagger.enabled}")
	Boolean swaggerEnabled;

	// @Autowired
	// RouteLocator routeLocator;
	@Autowired
	private ZuulRouteLocator zuulRoutesLocator;

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
				// 是否开启
				.enable(swaggerEnabled).select()
				// 扫描的路径包
				.apis(RequestHandlerSelectors.basePackage("com.fullstack.gateway"))
				// 指定路径处理PathSelectors.any()代表所有的路径
				.paths(PathSelectors.any()).build().pathMapping("/")
				.securitySchemes(Lists.newArrayList(apiKey()))
		        .securityContexts(Lists.newArrayList(securityContext()));
	}
	
	private ApiKey apiKey() {
		return new ApiKey("mykey", "Authorization", "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("/.*"))
				.build();
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Lists.newArrayList(new SecurityReference("mykey", authorizationScopes));
	}

	// 设置api信息
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("微服务路由网关：利用swagger2聚合API文档").description("fullstack")
				// 作者信息
				.contact(new Contact("fullstack", "", "")).version("1.0.0").build();
	}

	@Override
	public List<SwaggerResource> get() {
		List<SwaggerResource> resources = new ArrayList<>();
		resources.add(swaggerResource("rex-dedao-app-gateway", "/v2/api-docs", "1.0"));
		List<Route> routes = zuulRoutesLocator.getRoutes();
		routes.forEach(route -> {

			String name = route.getId();

			resources.add(swaggerResource(name, route.getFullPath().replace("**", "v2/api-docs"), "1.0"));

		});
		return resources;
	}

	private SwaggerResource swaggerResource(String name, String location, String version) {
		SwaggerResource swaggerResource = new SwaggerResource();
		swaggerResource.setName(name);
		swaggerResource.setLocation(location);
		swaggerResource.setSwaggerVersion(version);
		return swaggerResource;
	}
}
