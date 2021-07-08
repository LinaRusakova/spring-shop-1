package com.example.springshop1.utils;

/**
 * Класс Cart является утилитным - его назначение сформировать объект для показа пользователю. Объект Cart всегда неразрывно связан с каким-то Order.
 * idCart всегда копирует ID соответствуюего заказа*/
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;


@Data
@Component
@RequiredArgsConstructor
public class Cart {

    private UUID idCart;
    private List<Item> itemList;

    private int itemCount;
    private BigDecimal totalCartCost;

    public UUID getIdCart() {
        return idCart;
    }

    public void setIdCart(UUID idCart) {
        this.idCart = idCart;
    }

    public List<Item> getItemList() {
        return itemList;
    }
    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public BigDecimal getTotalCartCost() {
        BigDecimal totalCartCost= BigDecimal.valueOf(0);
        for (Item itemCost: this.getItemList()) {
            totalCartCost=totalCartCost.add(itemCost.getTotalPrice());
        }
        return totalCartCost;
    }







}