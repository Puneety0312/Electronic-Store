package com.electronic.store.services;

import com.electronic.store.dto.AddItemToCart;
import com.electronic.store.dto.CartDto;

public interface CartService {
    //add item
    CartDto addItemToCart(int userId, AddItemToCart request);

    CartDto getCart(int userId);
    //remove
    void removeItem(int cartItem);

    void clearCart(int userId);
}
