package com.ob.ejercicio3.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * <a href="http://localhost:8080/swagger-ui/index.html#/">...</a>
 */

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo())
                ;
    }

    public ApiInfo getApiInfo() {
        return new ApiInfo(
                "Laptop API",
                "List Service Laptops API",
                "3.0",
                "http://laptop.com/terms",
                new Contact("Laptop", "https://laptop.com", "contact@laptop.com"),
                "LICENSE",
                "LICENSE URL",
                Collections.emptyList());
    }
}
