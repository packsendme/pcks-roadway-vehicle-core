package com.packsendme.roadbrewa.vehicle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Vehicle_Application {

	public static void main(String[] args) {
		SpringApplication.run(Vehicle_Application.class, args);
	}
}

