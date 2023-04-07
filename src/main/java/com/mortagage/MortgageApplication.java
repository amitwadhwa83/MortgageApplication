package com.mortagage;

import com.mortagage.util.LoggingInterceptor;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
//http://localhost:8080/swagger-ui/index.html
public class MortgageApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(MortgageApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public OpenAPI mortageOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Mortgage approval and eligibility application")
                        .description("APIs to get the mortgage interest rates and eligibility check from ING")
                        .version("v0.0.1")
                        .contact(new Contact()
                                .name("mortgage_help@ing.com"))
                        .license(new License().name("ING 1.0").url("https://www.ing.com.tr/en/knowledge-base/mortgage")));
    }
}
