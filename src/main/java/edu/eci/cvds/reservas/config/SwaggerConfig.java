package edu.eci.cvds.reservas.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Mi API con Swagger",
        version = "1.0",
        description = "Documentación generada automáticamente con Swagger y OpenAPI"
    )
)
public class SwaggerConfig {
}