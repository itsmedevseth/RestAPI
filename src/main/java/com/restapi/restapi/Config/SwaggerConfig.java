package com.restapi.restapi.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.w3c.dom.DocumentType;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.print.Doc;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket productApi()
    {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.restapi.restapi.Controller"))
                .paths(PathSelectors.regex("/products.*")).build();
    }

    private ApiInfo apiInfo() {
       return new ApiInfoBuilder().title("Product API").description("Products CRUD Operation")
               .termsOfServiceUrl("Opern source").license("Piseth license").licenseUrl("seth.com").version("2.0").build();
    }
}
