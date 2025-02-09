package com.learntodrive.user_detail.learntodrive_user_detail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class LearntodriveUserDetailApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearntodriveUserDetailApplication.class, args);
	}

}
