package br.com.ciandt.desafio;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(apis())              
          .paths(PathSelectors.any())  
          .build()
          .apiInfo(apiInfo());
    }
    
    private Predicate<RequestHandler> apis() {
        return RequestHandlerSelectors.basePackage("br.com.ciandt.desafio.rest");
    }
    
   
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Desafio CI&T Spring Boot REST API")
                .description("Desafio CI&T Spring Boot REST API Desenvolvedor Java")
                .version("0.0.1")
                .license("Apache License Version 2.0")
                
                .contact(new Contact("Bernardo Nascimento dos Santos", "", "bernardo.luks@gmail.com"))
                .build();
    }
}
