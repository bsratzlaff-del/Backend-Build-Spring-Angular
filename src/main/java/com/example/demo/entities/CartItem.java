package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Set;


@Entity
@Table(name="cartitem")

public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vacation_id")
    private Vacation vacation;

    @ManyToMany
    @JoinTable(name = "excursion_cartitem",
            joinColumns = @JoinColumn(name = "cart_item_id"),
            inverseJoinColumns = @JoinColumn(name = "excursion_id"))
    private Set<Excursion> excursions;

    @ManyToOne
    @JoinColumn(name = "cart_item_id")
    private Cart cart;

    @Column(name = "create_date")
    @CreationTimestamp
    private Date create_date;

    public void setId(Long id) {
        this.id = id;
    }

    public void setVacation(Vacation vacation) {
        this.vacation = vacation;
    }

    public void setExcursions(Set<Excursion> excursions) {
        this.excursions = excursions;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    @Column(name = "last_update")
    @UpdateTimestamp
    private Date last_update;

    public Long getId() {
        return id;
    }

    public Vacation getVacation() {
        return vacation;
    }

    public Set<Excursion> getExcursions() {
        return excursions;
    }

    public Cart getCart() {
        return cart;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public Date getLast_update() {
        return last_update;
    }
}