package com.example.spice_shop_api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @OneToOne()
    private Product product;

    public CartItem(Long id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public CartItem(CartItem cartItemObject) {
        this.quantity = cartItemObject.getQuantity();
        this.product = cartItemObject.getProduct();
    }
}
