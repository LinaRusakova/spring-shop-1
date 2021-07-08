package com.example.springshop1.repositories;

import com.example.springshop1.models.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends CrudRepository <Order, UUID> {
    List<Order> findAll();

    Optional<Order> findById(UUID uuid);

    Optional<Order> findOrderByUserIdAndCartFlag(UUID uuid, boolean flag);
}
