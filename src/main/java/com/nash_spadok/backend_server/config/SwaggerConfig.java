package com.nash_spadok.backend_server.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Nash Spadok API")
                        .version("1.0")
                        .description("API documentation for Nash Spadok platform")
                        .termsOfService("")
                        .contact(new Contact()
                                .name("Vadym Pantielieienko")
                                .email("vadympantielieienko@gmail.com")
                                .url("https://www.linkedin.com/in/vadympantielieienko/"))
                        .license(new License()
                                .name("")
                                .url("")));
    }
}
