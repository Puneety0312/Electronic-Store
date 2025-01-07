package com.electronic.store.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {

    private int cartItemId;
    private ProductDto productDto;
    private int quantity;
    private int totalPrice;
}
