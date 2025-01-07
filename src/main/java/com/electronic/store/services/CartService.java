package com.electronic.store.services;

import com.electronic.store.dto.AddItemToCart;
import com.electronic.store.dto.CartDto;

public interface CartService {
    //add item
    CartDto addItemToCart(int userId, AddItemToCart request);
    //remove
    void removeItem(int userId, int cartItem);

    void clearCart(int userId);
}
