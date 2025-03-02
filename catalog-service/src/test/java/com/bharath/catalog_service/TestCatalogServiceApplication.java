package com.bharath.catalog_service;

import org.springframework.boot.SpringApplication;

public class TestCatalogServiceApplication {

	public static void main(String[] args) {
		//we call application main (Catalog service application) with test containers configuration which has db and infra setup
		SpringApplication.from(CatalogServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
