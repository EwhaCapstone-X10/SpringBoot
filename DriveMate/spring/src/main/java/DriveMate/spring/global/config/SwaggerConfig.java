package DriveMate.spring.global.config;

import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI DriveMateAPI() {
        Info info = new Info()
                .title("DriveMate Server API")
                .description("X10 DriveMate API 명세서")
                .version("1.0.0")
                .license(new License().name("Apache 2.0").url("http://springdoc.org"));

        /*
        String jwtSchemeName = "JWT TOKEN";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);
        Components components = new Components()
                .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
                        .name(jwtSchemeName)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT"));


         */
        return new OpenAPI()
                .info(info);
                //.addSecurityItem(securityRequirement)
                //.components(components);
    }
}
