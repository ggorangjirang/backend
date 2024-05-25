package com.elice.ggorangjirang;

import com.elice.ggorangjirang.global.config.EnvConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

@SpringBootTest
@PropertySource(value = {
		"classpath:env.yml",
}, factory = EnvConfig.class)
class GgorangjirangApplicationTests {

	@Test
	void contextLoads() {
	}

}
