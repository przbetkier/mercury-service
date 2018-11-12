package com.przbetkier.mercury;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO: Retries (+ cache?)
@SpringBootApplication
public class MercuryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MercuryApplication.class, args);
	}
}
