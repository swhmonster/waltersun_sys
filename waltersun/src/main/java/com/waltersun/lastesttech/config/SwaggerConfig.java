package com.waltersun.lastesttech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author walter
 * @date 2021-07-16
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("Walter Sun's WebSite")
                        .description("swagger-bootstrap-ui-demo RESTful APIs")
                        .termsOfServiceUrl("https://www.waltersun.cn")
                        .contact(new Contact("Walter","https://www.waltersun.cn","swh7941@qq.com"))
                        .version("1.0")
                        .build())
                .groupName("基础功能")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.waltersun.lastesttech.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
