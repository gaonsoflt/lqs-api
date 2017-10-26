package com.gaonsoft.lqs.api.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
//@ComponentScan(basePackageClasses = { FarmApiController.class })
public class SwaggerConfig {
	@Bean
	public Docket petApi() {
		ApiInfo info = new ApiInfo("하동축산방역시스템 Api", "Api document for App, 인식기서버, 키오스크 ", "v1", "localhost:8080",
				new Contact("Baek Woonsung", "", "ws.baek@gaonsoft.com"), "gaonsoft", "http://www.gaonsoft.com");

		List<ResponseMessage> responseMessages = new ArrayList<>();
		responseMessages.add(new ResponseMessageBuilder()
				.code(HttpStatus.BAD_REQUEST.value()).message(HttpStatus.BAD_REQUEST.getReasonPhrase()).build());
		responseMessages.add(new ResponseMessageBuilder()
				.code(HttpStatus.NOT_FOUND.value()).message(HttpStatus.NOT_FOUND.getReasonPhrase()).build());
		responseMessages.add(new ResponseMessageBuilder()
				.code(HttpStatus.INTERNAL_SERVER_ERROR.value()).message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).build());

		return new Docket(DocumentationType.SWAGGER_2)
				.select()
//					.apis(RequestHandlerSelectors.any())
					.apis(RequestHandlerSelectors.basePackage("com.gaonsoft.lqs.api"))
					.paths(PathSelectors.any())
					.build()
				.pathMapping("/")
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, responseMessages)
				.globalResponseMessage(RequestMethod.POST, responseMessages)
				.globalResponseMessage(RequestMethod.PATCH, responseMessages)
				.globalResponseMessage(RequestMethod.PUT, responseMessages)
				.globalResponseMessage(RequestMethod.DELETE, responseMessages)
				.apiInfo(info);
	}
}
