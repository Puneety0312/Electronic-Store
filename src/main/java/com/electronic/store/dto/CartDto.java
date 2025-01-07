package com.electronic.store.dto;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private String cartId;
    private UserDto userDto;
    private Date createdAt;
    private int totalPrice;
    private List<CartItemDto> items = new ArrayList<>();
}
