package com.bioprac;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BiopracApplication {

	private static final Logger logger = LoggerFactory.getLogger((BiopracApplication.class));

	public static void main(String[] args) {

		System.getenv().forEach((key, value) -> {
			logger.info("Key: " + key + ", value: " + value);
		});

		SpringApplication.run(BiopracApplication.class, args);

	}

}
