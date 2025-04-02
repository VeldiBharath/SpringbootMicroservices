package com.bharath.order_service;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "orders") //identifies configuration properties records, has to be declared in main
// start point(i.e. CatalogServiceApplication to be found. use @enableconfigiration at start point to be found)
public record ApplicationProperties(
        String catalogServiceUrl,
        String orderEventsExchange,
        String newOrdersQueue,
        String deliveredOrdersQueue,
        String cancelledOrdersQueue,
        String errorOrdersQueue) {}

