package com.elice.ggorangjirang.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info().title("Ggorangjirang API").version("1.0.0").description("API description"))
        .addSecurityItem(new SecurityRequirement().addList("AccessTokenKey"))
        .components(
            new io.swagger.v3.oas.models.Components()
                .addSecuritySchemes(
                    "AccessTokenKey",
                    new SecurityScheme()
                        .type(SecurityScheme.Type.APIKEY)
                        .in(SecurityScheme.In.HEADER)
                        .name("Authorization")
                        .description("JWT Authorization header. Example: \"{token}\"")));
  }

  @Bean
  public GroupedOpenApi api() {
    return GroupedOpenApi.builder().group("ggorangjirang").pathsToMatch("/**").build();
  }
}
