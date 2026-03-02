package com.example.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name="customers")

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @NotBlank(message = "First name is required")
    @Column(name = "customer_first_name")
    private String firstName;

    public Long getId() {
        return id;
    }

    public Division getDivision() {
        return division;
    }

    public Set<Cart> getCarts() {
        return carts;
    }

    @NotBlank(message = "Last name is required")
    @Column(name = "customer_last_name")
    private String lastName;

    @NotBlank(message = "Address is required")
    @Column(name = "address")
    private String address;

    @NotBlank(message = "Postal code is required")
    @Column(name = "postal_code")
    private String postal_code;

    @NotBlank(message = "Phone number is required")
    @Column(name = "phone")
    private String phone;

    @Column(name = "create_date")
    @CreationTimestamp
    private Date create_date;

    @Column(name = " last_update")
    @UpdateTimestamp
    private Date last_update;

    @NotNull(message = "Division is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "division_id")
    private Division division;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Cart> carts;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public String getPhone() {
        return phone;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public void setCarts(Set<Cart> carts) {
        this.carts = carts;
    }
}
