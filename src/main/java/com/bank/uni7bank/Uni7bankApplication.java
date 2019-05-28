package com.bank.uni7bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Uni7bankApplication {

	public static void main(String[] args) {
		SpringApplication.run(Uni7bankApplication.class, args);
	}

}
