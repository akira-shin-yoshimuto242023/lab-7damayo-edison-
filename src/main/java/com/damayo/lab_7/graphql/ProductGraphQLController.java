package com.damayo.lab_7.graphql;

// THESE IMPORTS MUST BE CORRECT:
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import com.damayo.lab_7.Product;        // Import Product from main package
import com.damayo.lab_7.ProductService; // Import ProductService from main package
import java.util.List;

@Controller
public class ProductGraphQLController {
    private final ProductService productService;

    public ProductGraphQLController(ProductService productService) {
        this.productService = productService;
    }
    @QueryMapping
    public List<Product> getAllProducts() {
        System.out.println("GraphQL: getAllProducts called");
        return productService.getAllProducts();
    }

    @QueryMapping
    public Product getProductById(@Argument Long id) {
        System.out.println("GraphQL: getProductById called with id: " + id);
        return productService.getProductById(id);
    }

    @QueryMapping
    public Integer getProductCount() {
        System.out.println("GraphQL: getProductCount called");
        return productService.getProductCount();
    }

    @MutationMapping
    public Product createProduct(@Argument String name, @Argument Double price) {
        System.out.println("GraphQL: createProduct called with name: " + name + ", price: " + price);
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        return productService.createProduct(product);
    }

    @MutationMapping
    public Product updateProduct(@Argument Long id, @Argument String name, @Argument Double price) {
        System.out.println("GraphQL: updateProduct called with id: " + id);
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        return productService.updateProduct(id, product);
    }

    @MutationMapping
    public Boolean deleteProduct(@Argument Long id) {
        System.out.println("GraphQL: deleteProduct called with id: " + id);
        return productService.deleteProduct(id);
    }
}