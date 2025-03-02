package com.bharath.catalog_service.web.controllers;


import com.bharath.catalog_service.domain.PagedResult;
import com.bharath.catalog_service.domain.Product;
import com.bharath.catalog_service.domain.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController // as this is a controller
@RequestMapping("/api/products") //Create request mapping so we dont have to specify /api/products each time

class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    PagedResult<Product> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo){
        return productService.getProducts(pageNo);
    }

}
