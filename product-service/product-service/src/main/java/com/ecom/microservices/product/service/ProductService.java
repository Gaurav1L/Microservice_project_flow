package com.ecom.microservices.product.service;

import com.ecom.microservices.product.dto.ProductRequest;
import com.ecom.microservices.product.dto.ProductResponse;
import com.ecom.microservices.product.entity.Product;
import com.ecom.microservices.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();

        productRepository.save(product);
        log.info("Product created successfully:");
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());

        /*public void createProduct(ProductRequest productRequest) {
        // --- OLD MAPPING STYLE 1: Using Standard Constructor ---
        // You have to remember the exact order of fields, which leads to bugs!
        Product product = new Product(
            productRequest.getName(),
            productRequest.getDescription(),
            productRequest.getPrice()
        );

        // --- OLD MAPPING STYLE 2: Using Setters ---
        // If your Product class doesn't have an all-args constructor, you do this:
        /*
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());

        productRepository.save(product);
         */
    }
    public List<ProductResponse> getAllProduct(){
        return productRepository.findAll()
                .stream()
                .map(product-> new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice())).toList();
    }

}
