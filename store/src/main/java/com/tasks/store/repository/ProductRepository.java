package com.tasks.store.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.tasks.store.model.Product;

@Repository
public class ProductRepository {

    private final List<Product> products = new ArrayList<>();
    private final AtomicLong nextId = new AtomicLong(4L);

    public ProductRepository() {
        products.add(new Product(1L, "Phone", 10000.0, "A nice smartphone", "Electronics"));
        products.add(new Product(2L, "Laptop", 50000.0, "A powerful laptop", "Electronics"));
        products.add(new Product(3L, "Headphones", 3000.0, "Good headphones", "Electronics"));
    }

    public List<Product> findAll() {
        return Collections.unmodifiableList(products);
    }

    public Optional<Product> findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(nextId.getAndIncrement());
            products.add(product);
        }
        return product;
    }

    public void deleteById(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }
}
