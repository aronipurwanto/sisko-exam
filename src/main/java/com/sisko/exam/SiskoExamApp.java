package com.sisko.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SiskoExamApp {

    public static void main(String[] args) {
        SpringApplication.run(SiskoExamApp.class, args);
    }
}