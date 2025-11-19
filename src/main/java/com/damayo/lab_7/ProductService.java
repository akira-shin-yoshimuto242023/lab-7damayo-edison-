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
    private final AtomicLong idCounter = new AtomicLong(4); // Start from 4 since we have 3 initial products

    // Initialize with 3 mock products
    public ProductService() {
        // Adding 3 initial mock products as specified in lab instructions
        productDatabase.put(1L, new Product(1L, "Laptop Pro", 1299.99));
        productDatabase.put(2L, new Product(2L, "Wireless Mouse", 129.99));
        productDatabase.put(3L, new Product(3L, "Mechanical Keyboard", 890.99));
    }

    // GET ALL Products - returns both initial and user-added products
    public List<Product> getAllProducts() {
        return new ArrayList<>(productDatabase.values());
    }

    // GET Product by ID
    public Product getProductById(Long id) {
        return productDatabase.get(id);
    }

    // CREATE new Product - allows user to input new data
    public Product createProduct(Product product) {
        // Validate input
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (product.getPrice() == null || product.getPrice() < 0) {
            throw new IllegalArgumentException("Product price must be positive");
        }

        // Generate new ID and create product
        Long newId = idCounter.getAndIncrement();
        Product newProduct = new Product(newId, product.getName(), product.getPrice());
        productDatabase.put(newId, newProduct);
        return newProduct;
    }

    // UPDATE existing Product
    public Product updateProduct(Long id, Product product) {
        if (!productDatabase.containsKey(id)) {
            return null; // Product not found
        }

        // Validate input
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (product.getPrice() == null || product.getPrice() < 0) {
            throw new IllegalArgumentException("Product price must be positive");
        }

        // Update the product
        Product updatedProduct = new Product(id, product.getName(), product.getPrice());
        productDatabase.put(id, updatedProduct);
        return updatedProduct;
    }

    // DELETE Product
    public boolean deleteProduct(Long id) {
        return productDatabase.remove(id) != null;
    }

    // Check if product exists
    public boolean productExists(Long id) {
        return productDatabase.containsKey(id);
    }

    // Get total product count (initial + user added)
    public int getProductCount() {
        return productDatabase.size();
    }
}