package com.gaonsoft.lqs.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;

@Import(SpringDataRestConfiguration.class)
@SpringBootApplication
public class LqsApiApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(LqsApiApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(LqsApiApplication.class, args);
	}
}
