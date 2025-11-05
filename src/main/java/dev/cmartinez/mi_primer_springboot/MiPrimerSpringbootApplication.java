package dev.cmartinez.mi_primer_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "dev.cmartinez")
@EntityScan(basePackages = "dev.cmartinez.entity")
@EnableJpaRepositories(basePackages = "dev.cmartinez.repository")
public class MiPrimerSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiPrimerSpringbootApplication.class, args);
    }
}
