package com.matdzie.peopleimportapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.matdzie.peopleimportapi"))
                .paths(PathSelectors.any())
                .build()
                .globalResponses(HttpMethod.GET, Arrays.asList(
                        new ResponseBuilder().code("200").build(),
                        new ResponseBuilder().code("404").description("Not found").build()))
                .globalResponses(HttpMethod.POST, Arrays.asList(
                        new ResponseBuilder().code("201").build(),
                        new ResponseBuilder().code("400").description("Invalid parameters").build(),
                        new ResponseBuilder().code("409").description("Already exists").build()))
                .pathMapping("/")
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {

        Contact contact = new Contact(
                "Mateusz Dziedzic",
                "https://www.linkedin.com/in/mateusz-dziedzic-9a1a3498/",
                "mateuszdziedzic90@gmail.com");

        return new ApiInfo(
                "people-import-api",
                "REST API that allows import people from swapi.dev",
                "1.0",
                "",
                contact,
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }
}
