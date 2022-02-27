package com.example.spice_shop_api.controllers;

import com.example.spice_shop_api.models.CartItem;
import com.example.spice_shop_api.services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CartItemController {
    private CartItemService cartItemService;

    @Autowired
    public void setCartItemService(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping("/cart-item/")
    public ResponseEntity<List<CartItem>> getAllCartItems() {
        return cartItemService.getAllCartItems();
    }

    @GetMapping("/cart-item/{cartItemId}/")
    public ResponseEntity<CartItem> getCartItem(@PathVariable(value = "cartItemId") Long cartItemId) {
        return cartItemService.getCartItemById(cartItemId);
    }

    @PostMapping("/cart-item/")
    public ResponseEntity<CartItem> createCartItem(@RequestBody CartItem cartItem) {
        return cartItemService.createCartItem(cartItem);
    }

    @PutMapping("/cart-item/{cartItemId}/")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable(value = "cartItemId") Long cartItemId, @RequestBody CartItem updatedCartItem) {
        return cartItemService.updateCartItem(cartItemId, updatedCartItem);
    }

    @DeleteMapping("/cart-item/{cartItemId}")
    public ResponseEntity<CartItem> deleteCartItem(@PathVariable(value = "cartItemId") Long cartItemId) {
        return cartItemService.deleteCartItem(cartItemId);
    }
}
