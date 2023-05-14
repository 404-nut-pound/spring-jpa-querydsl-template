package io.hskim.springjpaquerydsltemplate.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  private static final Contact DEFAULT_CONTACT = new Contact()
    .name("hskim")
    .email("hiuseongkim91@gmail.com")
    .url("https://github.com/404-nut-pound");

  private static final Info DEFAULT_API_INFO = new Info()
    .title("Spring Boot 3 JPA Querydsl Template")
    .description("REST Api Docs")
    .version("1.0")
    .contact(DEFAULT_CONTACT);

  // private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(
  //   Arrays.asList("application/json", "application/xml")
  // );

  @Bean
  public OpenAPI setOpenAPI() {
    return new OpenAPI()
      .info(DEFAULT_API_INFO)
      .components(
        new Components()
          .addSecuritySchemes(
            "bearer-key",
            new SecurityScheme()
              .type(SecurityScheme.Type.HTTP)
              .scheme("bearer")
              .bearerFormat("JWT")
          )
      );
  }
}
