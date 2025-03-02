package com.bharath.catalog_service.domain;

import java.math.BigDecimal;

//we never try to expose productEntity directly as they are strongly coupled with repository
//hence we created this product record to be exposed
public record Product(
        String code,
        String name,
        String description,
        String imageUrl,
        BigDecimal price) { }
