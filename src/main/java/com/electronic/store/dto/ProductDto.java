package com.electronic.store.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String productId;
    private String title;
    private String description;
    private int quantity;
    private boolean isStock;
    private double price;
    private int discountPrice;
    private Date date;
    private boolean isLive;
}
