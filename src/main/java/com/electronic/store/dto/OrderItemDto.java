package com.electronic.store.dto;

import com.electronic.store.entities.Order;
import com.electronic.store.entities.Product;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    private  int orderItemId;

    private  int quantity;

    private  int totalPrice;

    private Product product;

    private Order order;
}
