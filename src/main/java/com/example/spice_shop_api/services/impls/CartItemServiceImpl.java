package com.example.spice_shop_api.services.impls;

import com.example.spice_shop_api.models.CartItem;
import com.example.spice_shop_api.models.Category;
import com.example.spice_shop_api.models.Product;
import com.example.spice_shop_api.repositories.CartItemRepository;
import com.example.spice_shop_api.repositories.ProductRepository;
import com.example.spice_shop_api.services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService {
    private CartItemRepository cartItemRepository;
    private ProductRepository productRepository;

    @Autowired
    public void setCartItemRepository(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<CartItem> createCartItem(CartItem cartItemObject) {
        try {
            Product product = productRepository.findById(cartItemObject.getProduct().getId())
                    .orElseThrow(IllegalArgumentException::new);
            cartItemObject.setProduct(product);
            CartItem newCartItem = cartItemRepository
                    .save(new CartItem(cartItemObject));
            return new ResponseEntity<>(newCartItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
