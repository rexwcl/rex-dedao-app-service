package com.fullstack.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RexDedaoRegisterServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RexDedaoRegisterServerApplication.class, args);
	}

}
