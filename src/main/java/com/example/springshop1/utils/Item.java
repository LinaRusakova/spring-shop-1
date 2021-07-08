package com.example.springshop1.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Item {
    
    private UUID idProduct;
    private String nameOfProduct;
    private int count;
    private BigDecimal pricePerOne;

    
}
