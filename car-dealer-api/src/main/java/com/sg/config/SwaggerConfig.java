package com.sg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger 配置类
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		List<Parameter> parameters = new ArrayList<>();
		parameters.add(new ParameterBuilder()
				.name("userId")
				.description("用户ID")
				.modelRef(new ModelRef("string"))
				.parameterType("header")
				.required(false)
				.build());
		parameters.add(new ParameterBuilder()
				.name("token")
				.description("登录token")
				.modelRef(new ModelRef("string"))
				.parameterType("header")
				.required(false)
				.build());
		return new Docket(DocumentationType.SWAGGER_2).globalOperationParameters(parameters).select()
				.apis(RequestHandlerSelectors.basePackage("com.sg.api")).paths(PathSelectors.any()).build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Spring Boot中使用Swagger2构建RESTful APIs").description("car-dealer , swagger2")
				.termsOfServiceUrl("").version("1.0").build();
	}
}
