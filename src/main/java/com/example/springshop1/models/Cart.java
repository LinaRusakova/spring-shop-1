package com.example.springshop1.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.UUIDGenerator;

import javax.lang.model.element.Name;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
@Table(name = "cart")
public class Cart {
    @Id
    @GenericGenerator(name="UUID", strategy = "UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @OneToOne
    @JoinColumn(name="product_id")
    private Product product;

    @Column(name = "count")
    private int count;

    private BigDecimal pricePerProduct;

    private BigDecimal totalPrice;


}