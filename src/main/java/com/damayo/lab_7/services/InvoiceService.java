package com.damayo.lab_7.services;

import com.damayo.lab_7.Product;
import com.damayo.lab_7.entities.Customer;
import com.damayo.lab_7.entities.Invoice;
import com.damayo.lab_7.repositories.CustomerRepository;
import com.damayo.lab_7.repositories.InvoiceRepository;
import com.damayo.lab_7.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public InvoiceService(InvoiceRepository invoiceRepository,
                          CustomerRepository customerRepository,
                          ProductRepository productRepository) {
        this.invoiceRepository = invoiceRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Invoice createInvoice(Long customerId, List<Long> productIds) {
        // Get customer
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with id: " + customerId));

        // Get products
        List<Product> products = productRepository.findAllById(productIds);
        if (products.isEmpty()) {
            throw new IllegalArgumentException("No valid products found with ids: " + productIds);
        }

        // Calculate total
        Double totalAmount = products.stream()
                .mapToDouble(Product::getPrice)
                .sum();

        // Create invoice
        Invoice invoice = new Invoice();
        invoice.setCustomer(customer);
        invoice.setProducts(products);
        invoice.setInvoiceDate(LocalDateTime.now());
        invoice.setTotalAmount(totalAmount);

        return invoiceRepository.save(invoice);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }
}