package com.przbetkier.mercury;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class MercuryApplication {

    public static void main(String[] args) {
        SpringApplication.run(MercuryApplication.class, args);
    }
}
