package com.learntodrive.admin.learntodriveadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class LearntodriveadminApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearntodriveadminApplication.class, args);
	}

}
