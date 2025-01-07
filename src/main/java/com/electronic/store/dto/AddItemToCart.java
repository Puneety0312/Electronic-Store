package com.electronic.store.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AddItemToCart {
    private int productId;
    private int quantity;
}
