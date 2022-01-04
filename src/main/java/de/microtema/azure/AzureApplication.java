package de.microtema.azure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AzureApplication {

    public static void main(String[] args) {
        SpringApplication.run(AzureApplication.class, args);
    }
}
