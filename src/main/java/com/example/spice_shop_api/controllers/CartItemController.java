package com.example.spice_shop_api.controllers;

import com.example.spice_shop_api.models.CartItem;
import com.example.spice_shop_api.models.Category;
import com.example.spice_shop_api.services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CartItemController {
    private CartItemService cartItemService;

    @Autowired
    public void setCartItemService(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping("/cart-item/")
    public ResponseEntity<CartItem> createCartItem(@RequestBody CartItem cartItem) {
        return cartItemService.createCartItem(cartItem);
    }
}
