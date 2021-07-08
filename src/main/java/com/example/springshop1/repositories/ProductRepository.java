package com.example.springshop1.repositories;

import com.example.springshop1.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends CrudRepository <Product, UUID> {
    List<Product> findAll();

    Boolean deleteProductById(UUID id);

    Boolean addProduct(Product product);

    Optional<Product> findProductById(UUID id);
}
