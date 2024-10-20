package com.electronic.store.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    private String productId;
    private String title;
    private int quantity;
    private boolean isStock;
    @Column(length = 1000)
    private String description;
    private double price;
    private int discountPrice;
    private Date date;
    private boolean isLive;
}
