package com.example.springshop1.repository;

import com.example.springshop1.models.Cart;
import com.example.springshop1.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CartRepository extends CrudRepository <Cart, UUID> {

    public Product getProductById(UUID id);


}
