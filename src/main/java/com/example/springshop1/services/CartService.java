package com.example.springshop1.services;

import com.example.springshop1.models.Order;
import com.example.springshop1.models.Product;
import com.example.springshop1.utils.Cart;
import com.example.springshop1.utils.Item;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CartService {

    Cart cart;
    private final OrderService orderService;

    public CartService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void clearCart() {
        throw new UnsupportedOperationException();
    }

    public Boolean addListProductToCart(Set<Product> selectedItems) {
        Order newOrder = orderService.createOrderForCurrentUser();
        newOrder.setItems(selectedItems.stream().map(item -> {
            Item orderItem = new Item();
            orderItem.setIdProduct(item.getId());
            orderItem.setCount(item.getCount());
            orderItem.setPricePerOne(item.getPrice());
            return orderItem;
        }).collect(Collectors.toList()));

        return true;

    }
}
