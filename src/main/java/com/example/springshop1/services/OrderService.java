package com.example.springshop1.services;

import com.example.springshop1.models.Order;
import com.example.springshop1.models.Product;
import com.example.springshop1.repositories.OrderRepository;
import com.example.springshop1.utils.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserService userService;
    private final CartService cartService;
    protected final OrderRepository orderRepository;


    public Order createOrderForCurrentUser() {
        // получить из репозитория ордер с флагом корзины тру, если такого нет, то создать новый ордер с флагом tru
        Optional<Order> cartFlag = orderRepository.findOrderByUserIdAndCartFlag(UUID.randomUUID(), true);
        if (cartFlag.isPresent()) {
            return orderRepository.findOrderByUserIdAndCartFlag(UUID.randomUUID(), true).get();
        } else {
            Order newCartOrder = new Order();
            newCartOrder.setId(UUID.randomUUID());
            newCartOrder.getOrderSum();
            newCartOrder.setUser(null);
            newCartOrder.setAddress(null);
            newCartOrder.setPhoneNumber(null);
            newCartOrder.setCartFlag(true);
            return newCartOrder;
        }
    }


    public void saveOrderToDB(Order order) {

        orderRepository.save(order);
        cartService.clearCart();
    }

    // Параметр 'user' пока не используется
//    public List<Product> getCartFromDB(User user) {
////        this.products.addAll(cartRepository.findAll().forEach(cart -> cart.););
//        return products;
//    }

    public void addProduct(Set<Product> products) {
        Order newOrder = new Order();
        newOrder.setItems(products.stream().map(
                product -> {
                    Item item = new Item();
                    item.setCount(1);
                    item.setNameOfProduct(product.getName());
                    item.setIdProduct(product.getId());
                    return item;
                }
        ).collect(Collectors.toList()));
    }

    public void deleteProduct(Product product) {
        products.removeIf(p -> p.getId().equals(product.getId()));
    }

    public void increaseProductCount(Product product) {
        for (Product innerProduct : products) {
            if (product.getId().equals(innerProduct.getId())) {
                innerProduct.incrementCount();
                return;
            }
        }
    }

    public void decreaseProductCount(Product product) {
        for (Product innerProduct : products) {
            if (product.getId().equals(innerProduct.getId())) {
                innerProduct.decreaseCount();
                return;
            }
        }
    }

    public List<Product> getProducts() {
        throw new UnsupportedOperationException();
    }


}
