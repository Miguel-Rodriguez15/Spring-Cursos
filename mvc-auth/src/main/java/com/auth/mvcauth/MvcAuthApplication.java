package com.auth.mvcauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MvcAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcAuthApplication.class, args);
	}

}
