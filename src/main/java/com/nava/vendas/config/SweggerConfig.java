package com.nava.vendas.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SweggerConfig {

	@Bean
	public Docket docket() {//vai receber as configuações

		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)//estou falando que não vou utilizar a msg padrões
				.select()
					.apis(RequestHandlerSelectors.basePackage("com.nava.vendas.rest.controller"))
				.paths(PathSelectors.any())
				.build()
				.securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(apiKey()))
				.apiInfo(apiInfo());
		
	}

	private ApiInfo apiInfo() {// É a configuração do Docket

		return new ApiInfoBuilder()
				.title("Vendas Api")
				.description("Api do projeto de vendas")
				.version("1.0")
				.contact(contac())
				.build();
		
	}

	private Contact contac() {// vai eviar os dados do contato do desenvolvedor

		return new Contact("Felipe Lino", "https://github.com/flino1934", "f.lino1934@hotmail.com");

	}
	
	public ApiKey apiKey() {
		
		return new ApiKey("JWT","Authorization","header");
		
	}
	
	private SecurityContext securityContext() {
		
		return SecurityContext.builder()
				.securityReferences(defaultAuth())
				.forPaths(PathSelectors.any())
				.build();
		
	}
	
	 private List<SecurityReference> defaultAuth(){
	        AuthorizationScope authorizationScope = new AuthorizationScope(
	                "global", "accessEverything");
	        AuthorizationScope[] scopes = new AuthorizationScope[1];
	        scopes[0] = authorizationScope;
	        SecurityReference reference = new SecurityReference("JWT", scopes);
	        List<SecurityReference> auths = new ArrayList<>();
	        auths.add(reference);
	        return auths;
	    }
}
