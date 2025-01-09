package com.pmp.kafka_producer_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pmp.kafka_producer_service.model.CreateProductRestModel;
import com.pmp.kafka_producer_service.service.interfaces.ProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    public ResponseEntity<String> postMethodName(@RequestBody CreateProductRestModel entity) {
        String productId = this.productService.createProduct(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }

}
