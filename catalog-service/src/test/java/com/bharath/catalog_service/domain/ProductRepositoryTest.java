package com.bharath.catalog_service.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;


import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest(
        properties = {
                "spring.test.database.replace=none",
                "spring.datasource.url=jdbc:tc:postgresql:16-alpine:///db",
        })
@Sql("/test-data.sql")
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository; // Use field injection for the repository

    @Test
    public void should_find_product_by_code() {
        ProductEntity product = productRepository.findByCode("P100").orElseThrow();
        assertEquals("P100", product.getCode());
        assertEquals("The Hunger Games", product.getName());
        assertEquals("Winning will make you famous. Losing means certain death...", product.getDescription());
    }
}