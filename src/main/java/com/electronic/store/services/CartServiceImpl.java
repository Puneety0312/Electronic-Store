package com.electronic.store.services;

import com.electronic.store.dto.AddItemToCart;
import com.electronic.store.dto.CartDto;
import com.electronic.store.entities.Cart;
import com.electronic.store.entities.CartItem;
import com.electronic.store.entities.Product;
import com.electronic.store.entities.User;
import com.electronic.store.repositories.CartItemRepository;
import com.electronic.store.repositories.CartRepository;
import com.electronic.store.repositories.ProductRepository;
import com.electronic.store.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements  CartService
{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartDto addItemToCart(int userId, AddItemToCart request) {
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
        String productId = request.getProductId();
        int quantity = request.getQuantity();
        if(quantity<=0){
            throw new EmptyStackException();
        }
        Product product = productRepository.findById(productId).orElseThrow(()-> new RuntimeException("Product not found"));
        Cart cart = null;
        try {
            cart= cartRepository.findByUser(user).get();
        }
        catch (NoSuchElementException e){
            cart = new Cart();
            cart.setCartId(UUID.randomUUID().toString());
            cart.setCreatedAt(new Date());
        }
        List<CartItem> itemList = cart.getItems();
        AtomicBoolean flag = new AtomicBoolean(false);
        List<CartItem> updatedItems = itemList.stream()
                .map(item -> {
                    if(item.getProduct().getProductId().equals(productId)){
                        item.setQuantity(item.getQuantity()+quantity);
                        item.setTotalPrice(item.getQuantity()+quantity*product.getDiscountPrice());
                        flag.set(true);
                    }
                    return item;
                })
                .collect(Collectors.toList());
        cart.setItems(updatedItems);
        if(!flag.get()){
            CartItem cartItem = CartItem.builder()
                    .quantity(quantity)
                    .totalPrice(quantity*product.getDiscountPrice())
                    .cart(cart)
                    .product(product)
                    .build();
            cart.getItems().add(cartItem);
        }
        cart.setUser(user);
        Cart cart1 = cartRepository.save(cart);
        return modelMapper.map(cart1 , CartDto.class);
    }

    @Override
    public CartDto getCart(int userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(()-> new RuntimeException("Cart not found"));
        return modelMapper.map(cart, CartDto.class);
    }

    @Override
    public void removeItem(int cartItem) {
        CartItem cartItem1 = cartItemRepository.findById(cartItem).orElseThrow(()-> new RuntimeException("Cart item not found"));
        cartItemRepository.delete(cartItem1);
    }

    @Override
    public void clearCart(int userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(()-> new RuntimeException("Cart not found"));
        cart.getItems().clear();
        cartRepository.save(cart);
    }
}
