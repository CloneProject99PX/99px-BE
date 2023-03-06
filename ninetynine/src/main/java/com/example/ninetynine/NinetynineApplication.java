package com.example.ninetynine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NinetynineApplication {

	public static void main(String[] args) {
		SpringApplication.run(NinetynineApplication.class, args);
	}

}
