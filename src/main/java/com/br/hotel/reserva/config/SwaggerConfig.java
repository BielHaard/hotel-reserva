package com.br.hotel.reserva.config;

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
                        .title("API de Reservas de Hotel")
                        .description("API para gestão de reservas em hotéis")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Suporte Hotel Reserva")
                                .email("suporte@hotelreserva.com")
                                .url("https://hotelreserva.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentação Completa")
                        .url("https://hotelreserva.com/docs"));
    }
}