package com.example.springshop1.services;

import com.example.springshop1.models.Product;
import com.example.springshop1.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> getAllProduct() {
        return List.copyOf(productRepository.findAll());
    }

    public Product getProductById(UUID id) {
        return productRepository.findProductById(id).get();
    }

    //Service for role "ADMIN"
    public Boolean deleteProductById(UUID id) {
        return productRepository.deleteProductById(id);
    }

    //Service for role "ADMIN"
    public Boolean addProduct(Product product) {
        return productRepository.addProduct(product);
    }

    public void save(Product product) {
        productRepository.save(product);
    }
}
