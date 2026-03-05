package com.example.demo.services;

import jakarta.validation.Valid;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import lombok.Data;
import java.util.Set;

@Data
public class Purchase {
    @Valid
    private Customer customer;
    private Cart cart;
    private Set<CartItem>cartItems;

}
