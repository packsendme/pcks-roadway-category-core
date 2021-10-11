package com.packsendme.roadway.category;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Category_Application {

	public static void main(String[] args) {
		SpringApplication.run(Category_Application.class, args);
	}
	
}

