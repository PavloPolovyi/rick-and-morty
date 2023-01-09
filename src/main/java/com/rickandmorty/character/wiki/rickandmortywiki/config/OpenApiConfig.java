package com.rickandmorty.character.wiki.rickandmortywiki.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@RequiredArgsConstructor
@Configuration
public class OpenApiConfig {
    private final Environment environment;

    @Bean
    public OpenAPI rickAndMortyOpenApi() {
        return new OpenAPI()
                .info(new Info().title(environment.getProperty("api.title"))
                        .description(environment.getProperty("api.description"))
                        .version(environment.getProperty("api.version"))
                        .license(new License().name(environment.getProperty("api.license"))
                                .url(environment.getProperty("api.license.url"))))
                .externalDocs(new ExternalDocumentation()
                        .description(environment.getProperty("api.github.title"))
                        .url(environment.getProperty("api.github.url")));
    }
}
