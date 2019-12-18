package com.fullstack.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication()
@EnableZuulProxy
@EnableDiscoveryClient
public class RexDedaoApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(RexDedaoApiGatewayApplication.class, args);
	}

}
