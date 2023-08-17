package com.kfh.educationsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EducationSiteForKfhApplication {

	public static void main(String[] args) {
		SpringApplication.run(EducationSiteForKfhApplication.class, args);
	}

}
