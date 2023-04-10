package com.Springboot.MySql;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class UserApiConfig {
//	@Bean
//	public GroupedOpenApi pubicApi() {
//	  return GroupedOpenApi.builder()
//		.group("")
//		.pathsToMatch("/api/v1/auth**","/Files/**","/mail/**","/threadMail/**")
//		.build();
//		}
	
	
	 @Bean
     public OpenAPI springShopOpenAPI() {
           final String securitySchemeName = "bearerAuth";
         return new OpenAPI()
                 .info(new Info().title("Email File APIs")
                 .description("Thread Email and File operation sample application")
                 .version("v0.0.1")
                 .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                 .externalDocs(new ExternalDocumentation()
                 .description("Mail Documentation")
                 .url("https://github.com/20Mayur/SPRING"))
                 .addSecurityItem(new SecurityRequirement()
                         .addList(securitySchemeName))
                       .components(new Components()
                         .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                           .name(securitySchemeName)
                           .type(SecurityScheme.Type.HTTP)
                           .scheme("bearer")
                           .bearerFormat("JWT")));
     }

}
