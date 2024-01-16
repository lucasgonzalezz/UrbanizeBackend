package com.ecommerce.urbanize.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "cart")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int amount;

    @NotNull
    private double price;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "idProduct")
    private ProductEntity product;

    public CartEntity() {
    }

    public CartEntity(Long id, int amount, double price, UserEntity user, ProductEntity product) {
        this.id = id;
        this.amount = amount;
        this.price = price;
        this.user = user;
        this.product = product;
    }

    public CartEntity(int amount, double price, UserEntity user, ProductEntity product) {
        this.amount = amount;
        this.price = price;
        this.user = user;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

}