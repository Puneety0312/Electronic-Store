package com.electronic.store.controllers;
import com.electronic.store.dto.AddItemToCart;
import com.electronic.store.dto.ApiMessage;
import com.electronic.store.dto.CartDto;
import com.electronic.store.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{userId}")
    public ResponseEntity<CartDto> addItemToCart(@PathVariable int userId, @RequestBody AddItemToCart request){
       CartDto cartDto= cartService.addItemToCart(userId, request);
       return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<ApiMessage> removeItem(@PathVariable int itemId){
        cartService.removeItem(itemId);
        ApiMessage message = ApiMessage.builder()
                .message("Item removed")
                .status(HttpStatus.OK)
                .success(true)
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiMessage> clearCart(@PathVariable int userId){
        cartService.clearCart(userId);
        ApiMessage message = ApiMessage.builder()
                .message("Cart items deleted")
                .status(HttpStatus.OK)
                .success(true)
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getCart(@PathVariable  int userId){
        CartDto cartDto =cartService.getCart(userId);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

}
