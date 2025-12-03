package com.damayo.lab_7;

<<<<<<< HEAD
import com.damayo.lab_7.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
=======
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
>>>>>>> 3a1a4503e4a3b982f5248a8d9d40b628ed7643be

@Service
public class ProductService {

<<<<<<< HEAD
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
        initializeSampleData();
    }

    private void initializeSampleData() {
        if (productRepository.count() == 0) {
            productRepository.save(new Product(null, "Laptop Pro", 1299.99));
            productRepository.save(new Product(null, "Wireless Mouse", 129.99));
            productRepository.save(new Product(null, "Mechanical Keyboard", 890.99));
            System.out.println("Sample products initialized in database");
        }
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Transactional
    public Product createProduct(Product product) {
        validateProduct(product);
        product.setId(null); // Let database generate ID
        return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(Long id, Product product) {
        if (!productRepository.existsById(id)) {
            return null;
        }
        validateProduct(product);
        product.setId(id);
        return productRepository.save(product);
    }

    @Transactional
    public Boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean productExists(Long id) {
        return productRepository.existsById(id);
    }

    public int getProductCount() {
        return (int) productRepository.count();
    }

    private void validateProduct(Product product) {
=======
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
>>>>>>> 3a1a4503e4a3b982f5248a8d9d40b628ed7643be
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (product.getPrice() == null || product.getPrice() < 0) {
            throw new IllegalArgumentException("Product price must be positive");
        }
<<<<<<< HEAD
=======

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
>>>>>>> 3a1a4503e4a3b982f5248a8d9d40b628ed7643be
    }
}