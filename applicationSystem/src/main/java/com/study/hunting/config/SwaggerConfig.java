package com.study.hunting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public SwaggerConfig() {
    }

    @Bean
    public Docket docket(Environment environment) {
        Profiles profiles = Profiles.of("dev", "test");
        boolean flag = environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfo(
                        "application modal",
                        "user's application and audit",
                        "1.0",
                        "urn:tos",
                        new Contact("xcc", "baidu.com", "root@163.com"),
                        "Apache 2.0",
                        "www.baidu.com",
                        new ArrayList<>()))
                .groupName("application")
                .enable(flag)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.study.hunting.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
