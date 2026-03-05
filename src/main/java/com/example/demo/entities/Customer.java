package com.example.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name="customers")
@Getter
@Setter

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @NotBlank(message = "First name is required")
    @Column(name = "customer_first_name")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Column(name = "customer_last_name")
    private String lastName;

    @NotBlank(message = "Address is required")
    @Column(name = "address")
    private String address;

    @NotBlank(message = "Postal code is required")
    @Column(name = "postal_code")
    @JsonProperty("postal_code")
    private String postalCode;

    @NotBlank(message = "Phone number is required")
    @Column(name = "phone")
    private String phone;

    @Column(name = "create_date")
    @CreationTimestamp
    private Date create_date;

    @Column(name = "last_update")
    @UpdateTimestamp

    private Date last_update;

    @NotNull(message = "Division is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "division_id")
    private Division division;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Cart> carts;

    public void add(Cart cart) {
        if (cart != null) {
            if (carts == null) {
                carts = new HashSet<>();
            }
            carts.add(cart);
            cart.setCustomer(this);
        }
    }
}
