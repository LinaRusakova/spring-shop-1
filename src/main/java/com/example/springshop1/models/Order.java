package com.example.springshop1.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    @OneToMany(mappedBy = "order")
//    @Cascade(org.hibernate.annotations.CascadeType.ALL)
//    private List<Cart> items;

    @Column(name = "address")
    private String address;

    @Column(name = "contact_phone")
    private String phoneNumber;

    @Column(name = "order_sum")
    private BigDecimal orderSum;


//    public void addItemsFromCart(Cart cart) {
//        this.setItems(List.copyOf(cart.getItems()));
//        this.setOrderSum(cart.getSum());
//    }
}
