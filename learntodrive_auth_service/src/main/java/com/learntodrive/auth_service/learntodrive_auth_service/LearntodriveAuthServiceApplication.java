package com.learntodrive.auth_service.learntodrive_auth_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class LearntodriveAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearntodriveAuthServiceApplication.class, args);
	}

}
