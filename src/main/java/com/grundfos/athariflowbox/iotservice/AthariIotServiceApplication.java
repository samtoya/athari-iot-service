package com.grundfos.athariflowbox.iotservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@OpenAPIDefinition(info = @Info(description = "Athari.Flowbox IoT Service API", version = "1.0", title = "Athari.Flowbox IoT Service API"))
public class AthariIotServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AthariIotServiceApplication.class, args);
    }
}
