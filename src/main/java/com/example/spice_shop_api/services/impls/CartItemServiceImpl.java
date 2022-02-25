package com.example.spice_shop_api.services.impls;

import com.example.spice_shop_api.models.CartItem;
import com.example.spice_shop_api.models.Category;
import com.example.spice_shop_api.repositories.CartItemRepository;
import com.example.spice_shop_api.services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService {
    private CartItemRepository cartItemRepository;

    @Autowired
    public void setCartItemRepository(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public ResponseEntity<CartItem> createCartItem(CartItem cartItemObject) {
        try {
            CartItem newCartItem = cartItemRepository
                    .save(new CartItem(cartItemObject));
            return new ResponseEntity<>(newCartItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
