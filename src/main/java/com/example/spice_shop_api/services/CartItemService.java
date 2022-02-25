package com.example.spice_shop_api.services;

import com.example.spice_shop_api.models.CartItem;
import org.springframework.http.ResponseEntity;

public interface CartItemService {
    ResponseEntity<CartItem> createCartItem(CartItem cartItemObject);
}
