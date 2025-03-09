package com.bharath.catalog_service.web.controllers;


import com.bharath.catalog_service.domain.PagedResult;
import com.bharath.catalog_service.domain.Product;
import com.bharath.catalog_service.domain.ProductNotFoundException;
import com.bharath.catalog_service.domain.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



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

    @GetMapping("/{code}")
    ResponseEntity<Product> getProductByCode(@PathVariable String code){
        return productService.getProductByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(()->new ProductNotFoundException("Product not found with code: "+code));
    }
}
