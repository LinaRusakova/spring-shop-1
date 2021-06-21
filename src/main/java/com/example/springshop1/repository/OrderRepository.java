package com.example.springshop1.repository;

import com.example.springshop1.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends CrudRepository <Product, UUID> {
    List<Product> findAll();
}
