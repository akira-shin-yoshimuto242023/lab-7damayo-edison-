package com.damayo.lab_7;

import com.damayo.lab_7.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ProductService {

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
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (product.getPrice() == null || product.getPrice() < 0) {
            throw new IllegalArgumentException("Product price must be positive");
        }
    }
}