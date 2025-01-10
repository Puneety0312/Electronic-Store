package com.electronic.store.services;

import com.electronic.store.dto.CreateOrderRequest;
import com.electronic.store.dto.OrderDto;
import com.electronic.store.dto.OrderUpdateRequest;
import com.electronic.store.dto.PageableResponse;

import java.util.List;

public interface OrderService {
    OrderDto getOrder(String orderId);

    //create order
    OrderDto createOrder(CreateOrderRequest orderDto);

    //remove order
    void removeOrder(String orderId);

    //get orders of user
    List<OrderDto> getOrdersOfUser(int userId);

    //get orders
    PageableResponse<OrderDto> getOrders(int pageNumber, int pageSize, String sortBy, String sortDir);

    OrderDto updateOrder(String orderId, OrderUpdateRequest request);
    OrderDto updateOrder(String orderId, OrderDto request);
}
