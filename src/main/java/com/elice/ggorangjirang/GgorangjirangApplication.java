package com.elice.ggorangjirang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GgorangjirangApplication {

	public static void main(String[] args) {
		SpringApplication.run(GgorangjirangApplication.class, args);
	}

}
