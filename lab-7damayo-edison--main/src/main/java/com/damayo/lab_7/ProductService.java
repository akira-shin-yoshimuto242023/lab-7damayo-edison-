package com.damayo.lab_7;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {

    private final Map<Long, Product> productDatabase = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(4);

    public ProductService() {
        productDatabase.put(1L, new Product(1L, "Laptop Pro", 1299.99));
        productDatabase.put(2L, new Product(2L, "Wireless Mouse", 129.99));
        productDatabase.put(3L, new Product(3L, "Mechanical Keyboard", 890.99));
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(productDatabase.values());
    }

    public Product getProductById(Long id) {
        return productDatabase.get(id);
    }

    public Product createProduct(Product product) {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (product.getPrice() == null || product.getPrice() < 0) {
            throw new IllegalArgumentException("Product price must be positive");
        }

        Long newId = idCounter.getAndIncrement();
        Product newProduct = new Product(newId, product.getName(), product.getPrice());
        productDatabase.put(newId, newProduct);
        return newProduct;
    }

    public Product updateProduct(Long id, Product product) {
        if (!productDatabase.containsKey(id)) {
            return null;
        }

        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (product.getPrice() == null || product.getPrice() < 0) {
            throw new IllegalArgumentException("Product price must be positive");
        }

        Product updatedProduct = new Product(id, product.getName(), product.getPrice());
        productDatabase.put(id, updatedProduct);
        return updatedProduct;
    }

    public Boolean deleteProduct(Long id) {
        return productDatabase.remove(id) != null;
    }

    public boolean productExists(Long id) {
        return productDatabase.containsKey(id);
    }

    public int getProductCount() {
        return productDatabase.size();
    }
}