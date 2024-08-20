package com.emezon.stock;

import org.junit.jupiter.api.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class StockApplicationTests {

	@Test
	void contextLoads() {
		// Initializes the Spring application context
		try (ConfigurableApplicationContext context = new SpringApplicationBuilder(StockApplication.class)
				.run()) {
			// Asserts that the context is not null
			assertNotNull(context);
		}
	}

}
