package com.ecom.microservices.product.controller;


import com.ecom.microservices.product.dto.ProductRequest;
import com.ecom.microservices.product.dto.ProductResponse;
import com.ecom.microservices.product.entity.Product;
import com.ecom.microservices.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest product){
        ProductResponse productResponse = productService.createProduct(product);
        return new ResponseEntity<>(productResponse,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
       return ResponseEntity.ok(productService.getAllProduct());


    }
}
