package com.example.demo.services;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.CartRepository;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.StatusType;
import com.example.demo.entities.Customer;
import com.example.demo.services.Purchase;
import com.example.demo.services.PurchaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public CheckoutServiceImpl(CartRepository cartRepository, CustomerRepository customerRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
    }
    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        Cart cart = purchase.getCart();
        Customer customer = purchase.getCustomer();
        Set<CartItem> cartItems = purchase.getCartItems();

        if (customer.getDivision() == null){
            com.example.demo.entities.Division dummyDivision = new com.example.demo.entities.Division();
            dummyDivision.setId(2L);

            com.example.demo.entities.Country dummyCountry = new com.example.demo.entities.Country();
            dummyCountry.setId(1L);
            customer.setDivision(dummyDivision);
        }

        cart.setId(null);
        customer.setId(null);

        if (customer.getCarts() != null) {
            customer.getCarts().clear();
        }

        String orderTrackingNumber = UUID.randomUUID().toString();
        cart.setOrderTrackingNumber(orderTrackingNumber);
        cart.setStatus(StatusType.ordered);

        cartItems.forEach(item -> {
            item.setCart(cart);
            item.setId(null);
        });

        customer.add(cart);

        customerRepository.save(customer);

        return new PurchaseResponse(orderTrackingNumber);
    }
}
