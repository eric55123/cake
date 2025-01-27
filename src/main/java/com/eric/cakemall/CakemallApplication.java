package com.eric.cakemall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.eric.cakemall.repository")
public class CakemallApplication {

    public static void main(String[] args) {
        SpringApplication.run(CakemallApplication.class, args);
    }

}
