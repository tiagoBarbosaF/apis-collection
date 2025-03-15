package com.apis.apiscollection.infrastructure.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "API Collection",
                version = "0.1",
                description = "API Collection documentation"
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Local server")
        }
)
public class OpenApiConfig {
}
