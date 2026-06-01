package com.tasks.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tasks.store.exception.ResourceNotFoundException;
import com.tasks.store.model.Product;
import com.tasks.store.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Value("${store.name}")
    private String storeName;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public String getStoreName() {
        return storeName;
    }
}
