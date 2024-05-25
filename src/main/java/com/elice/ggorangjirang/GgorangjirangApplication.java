package com.elice.ggorangjirang;

import com.elice.ggorangjirang.global.config.EnvConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@PropertySource(value = {
		"classpath:env.yml",
}, factory = EnvConfig.class)
public class GgorangjirangApplication {

	public static void main(String[] args) {
		SpringApplication.run(GgorangjirangApplication.class, args);
	}

}
