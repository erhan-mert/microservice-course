package com.example.lottery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableScheduling
public class LotteryClientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LotteryClientServiceApplication.class, args);
	}

}
