package com.example.spice_shop_api.services;

import com.example.spice_shop_api.models.CartItem;
import com.example.spice_shop_api.models.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartItemService {
    ResponseEntity<List<CartItem>> getAllCartItems();
    ResponseEntity<CartItem> getCartItemById(Long cartItemId);
    ResponseEntity<CartItem> createCartItem(CartItem cartItemObject);
    ResponseEntity<CartItem> updateCartItem(Long cartItemId, CartItem cartItemChanges);
    ResponseEntity<CartItem> deleteCartItem(Long cartItemId);
}
