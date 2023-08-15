package com.unibuc.fraudservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.unibuc.fraudservice")
@EnableDiscoveryClient
public class FraudServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FraudServiceApplication.class, args);
	}

}
