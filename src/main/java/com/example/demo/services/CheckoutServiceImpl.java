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

        cart.setId(null);
        String orderTrackingNumber = UUID.randomUUID().toString();
        cart.setOrderTrackingNumber(orderTrackingNumber);
        cart.setStatus(StatusType.ordered);

        if (cartItems != null) {
            cartItems.forEach(item -> {
                item.setCart(cart);
                cart.add(item);
                item.setId(null);
            });
        }

        cart.setCustomer(customer);
        customer.add(cart);


        if (customer != null && customer.getDivision()== null){
            com.example.demo.entities.Division dummyDivision = new com.example.demo.entities.Division();
            dummyDivision.setId(2L);
            customer.setDivision(dummyDivision);
        }








        cartRepository.save(cart);

        return new PurchaseResponse(orderTrackingNumber);
    }
}
