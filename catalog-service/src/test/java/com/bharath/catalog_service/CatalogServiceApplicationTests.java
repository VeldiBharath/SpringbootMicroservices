package com.bharath.catalog_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;


@SpringBootTest
@Import(TestcontainersConfiguration.class)
class CatalogServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
