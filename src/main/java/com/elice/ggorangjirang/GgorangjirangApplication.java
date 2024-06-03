package com.elice.ggorangjirang;

import com.elice.ggorangjirang.global.config.EnvConfig;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@PropertySource(value = {
		"classpath:env.yml",
}, factory = EnvConfig.class)
@OpenAPIDefinition(servers = {@Server(url = "https://ggorangjirang.duckdns.org", description = "GRJR")})
@EnableScheduling
public class GgorangjirangApplication {

	public static void main(String[] args) {
		SpringApplication.run(GgorangjirangApplication.class, args);
	}
}
