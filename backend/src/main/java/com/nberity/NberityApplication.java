package com.nberity;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableBatchProcessing
@SpringBootApplication
@EntityScan("com.nberity.*")
@EnableJpaRepositories("com.nberity.*")
public class NberityApplication {

    public static void main(String[] args) {
        SpringApplication.run(NberityApplication.class, args);
    }

}
