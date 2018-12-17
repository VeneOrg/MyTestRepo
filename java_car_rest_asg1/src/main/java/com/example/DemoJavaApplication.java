package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

@EnableEntityLinks
@EnableHypermediaSupport(type = HypermediaType.HAL)
@SpringBootApplication
public class DemoJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoJavaApplication.class, args);
	}
}

//mvn spring-boot:run -Drun.arguments=--server.port=9090
