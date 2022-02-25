package com.example.spice_shop_api.services.impls;

import com.example.spice_shop_api.models.CartItem;
import com.example.spice_shop_api.models.Product;
import com.example.spice_shop_api.repositories.CartItemRepository;
import com.example.spice_shop_api.repositories.ProductRepository;
import com.example.spice_shop_api.services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<CartItem>> getAllCartItems() {
        try{
            List<CartItem> cartItems = new ArrayList<>(cartItemRepository.findAll());

            if (cartItems.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cartItems, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<CartItem> getCartItemById(Long cartItemId) {
        Optional<CartItem> cartItemObject = cartItemRepository.findById(cartItemId);
        return cartItemObject.map(cartItem -> new ResponseEntity<>(cartItem, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
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

    @Override
    public ResponseEntity<CartItem> updateCartItem(Long cartItemId, CartItem cartItemChanges) {
        Optional<CartItem> cartItemObject = cartItemRepository.findById(cartItemId);
        if (cartItemObject.isPresent()) {
            CartItem cartItemCurrent = cartItemObject.get();

            cartItemCurrent.setQuantity(cartItemChanges.getQuantity());

            return new ResponseEntity<>(cartItemRepository.save(cartItemCurrent), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<CartItem> deleteCartItem(Long cartItemId) {
        try {
            cartItemRepository.deleteById(cartItemId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
