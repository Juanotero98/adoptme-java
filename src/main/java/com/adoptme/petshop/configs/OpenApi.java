package com.adoptme.petshop.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "ADOPTME API",
                version = "1.0",
                description = "CRUD of users, ext users and pets"

        )
)
public class OpenApi {

}
