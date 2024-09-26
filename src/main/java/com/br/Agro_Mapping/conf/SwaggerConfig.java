package com.br.Agro_Mapping.conf;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Agro Mapping API")
                        .description("Documentação da API para o projeto Agro Mapping")
                        .version("v1.0")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentação Completa")
                        .url("https://Agro_Mapping.com/docs"));
    }

    @Bean
    public GroupedOpenApi itemPedidoApi() {
        return GroupedOpenApi.builder()
                .group("ItemPedido")
                .pathsToMatch("/itemPedido/**")
                .build();
    }

    @Bean
    public GroupedOpenApi pedidoApi() {
        return GroupedOpenApi.builder()
                .group("Pedido")
                .pathsToMatch("/pedido/**")
                .build();
    }
}
