package com.raphaelsilva.credit.system.configuration

import io.swagger.v3.oas.models.tags.Tag
import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Swagger3Config {

    @Bean
    fun openApi(): OpenAPI {
        return OpenAPI()
                .info(Info()
                              .title("Credit System API")
                              .description("Request your credit")
                              .version("v1.0"))
                .externalDocs(ExternalDocumentation()
                                      .description("Access DataBase")
                                      .url("http://localhost:8080/h2-console"))
                .tags(mutableListOf(
                        Tag().name("Customer").description("Customer EndPoints"),
                        Tag().name("Credit").description("Credit EndPoints")))
    }

    @Bean
    fun publicApi(): GroupedOpenApi? {
        return GroupedOpenApi
                .builder()
                .group("springcredtsystem-public")
                .pathsToMatch("/api/customers/**", "/api/credits/**")
                .build()
    }
}