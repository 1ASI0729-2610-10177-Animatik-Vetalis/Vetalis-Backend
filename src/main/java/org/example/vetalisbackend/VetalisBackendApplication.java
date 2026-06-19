package org.example.vetalisbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VetalisBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(VetalisBackendApplication.class, args);
    }

}
