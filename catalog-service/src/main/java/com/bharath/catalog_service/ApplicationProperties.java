package com.bharath.catalog_service;

import jakarta.validation.constraints.Min;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "catalog")  //identifies configuration properties records, has to be declared in main
// start point(i.e. CatalogServiceApplication to be found. use @enableconfigiration at start point to be found)

//This is to make the pageNo as configurable in application.properties file.
public record ApplicationProperties(
        @DefaultValue("10")
        @Min(1)
        int pageSize){ }
