package com.example.springshop1.models;

import com.example.springshop1.utils.Cart;
import com.example.springshop1.utils.Item;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GenericGenerator(name="UUID", strategy = "UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Item> items;

    @Column(name = "address")
    private String address;

    @Column(name = "contact_phone")
    private String phoneNumber;

    @Column(name = "order_sum")
    private BigDecimal orderSum;

    @Column(name = "cart_flag")
    private boolean cartFlag;


//    public void addItemsFromCart(Cart cart) {
//        this.setItems(List.copyOf(cart.getItems()));
//        this.setOrderSum(cart.getSum());
//    }
}
