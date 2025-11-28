package com.rubio.marsroverapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "API ROVER MARS",
                description = "Api que simula un Rover en Marte",
                contact = @Contact(
                        name = "Jesus Rubio",
                        email = "jesusantoniorubiot@gmail.com"
                ),
                version = "1.0",
                termsOfService = "MiPagina/terminos_y_servicios",
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        servers = {
                @Server(
                        description = "DEV SERVER",
                        url = "http://localhost:8080"
                )
        }
)
public class SwaggerConfig {
}
