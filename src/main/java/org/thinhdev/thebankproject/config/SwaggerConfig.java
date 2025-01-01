package org.thinhdev.thebankproject.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("My API")
                        .version("1.0")
                        .description("This is a sample Spring Boot RESTful service using OpenAPI 3.")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                        .contact(new Contact()
                                .name("Thinh Dev")
                                .email("thinhproee@gmail.com")
                                .url("https://github.com/thinhdev")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project Documentation")
                        .url("https://example.com"));
    }
}
