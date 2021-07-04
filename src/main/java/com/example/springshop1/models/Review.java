package com.example.springshop1.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GenericGenerator(name="UUID", strategy = "UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @OneToOne
    @JoinColumn(name="product_id")
    private Product product;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}