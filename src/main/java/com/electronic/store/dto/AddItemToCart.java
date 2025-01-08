package com.electronic.store.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AddItemToCart {
    private String productId;
    private int quantity;
}
