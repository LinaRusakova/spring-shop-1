package com.example.springshop1.service;

import com.example.springshop1.models.Cart;
import com.example.springshop1.models.Product;
import com.example.springshop1.models.User;
import com.example.springshop1.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private List<Product> products;
    private final UserService userService;
    private final Cart cart;
    private CartRepository cartRepository;



    public void addProduct(Product product) {
        products.add(product);
    }


    public void saveCartToDB(List<Product> productsList) {

        cartRepository.save(cart);
        products.clear();
    }

    // Параметр 'user' пока не используется
//    public List<Product> getCartFromDB(User user) {
////        this.products.addAll(cartRepository.findAll().forEach(cart -> cart.););
//        return products;
//    }

    public void addProduct(Set<Product> products) {
        this.products.addAll(products.stream().map(
                product -> {
                    var newProduct = new Product();
                    newProduct.setCount(1);
                    newProduct.setName(product.getName());
                    newProduct.setId(product.getId());
                    return newProduct;
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

//    public java.util.List<Product> getProducts() {
//        return products;
//    }
}
