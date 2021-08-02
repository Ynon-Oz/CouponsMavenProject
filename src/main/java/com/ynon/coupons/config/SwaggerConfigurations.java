package com.ynon.coupons.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfigurations {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                // .apis( RequestHandlerSelectors.any() ) // Expose all APIs
                .apis(RequestHandlerSelectors.basePackage("com.ynon.coupons"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(
                        new ApiInfo("CouponSystem REST API",
                                "In this API we ",
                                "v1.0", "",
                                new Contact("Ynon Oz", "none", "ynonoz@gmail.com"),
                                "", "", Collections.emptyList()));
    }
}