package com.ecommerce.urbanize.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "cart")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    /**
     * Default constructor.
     */
    public CartEntity() {
    }

    /**
     * Constructor with parameters for full entity initialization.
     *
     * @param id      The cart's ID.
     * @param amount  The cart's amount.
     * @param user    The user associated with the cart.
     * @param product The product in the cart.
     */
    public CartEntity(Long id, int amount, UserEntity user, ProductEntity product) {
        this.id = id;
        this.amount = amount;
        this.user = user;
        this.product = product;
    }

    /**
     * Constructor with parameters for partial entity initialization.
     *
     * @param amount  The cart's amount.
     * @param user    The user associated with the cart.
     * @param product The product in the cart.
     */
    public CartEntity(int amount, UserEntity user, ProductEntity product) {
        this.amount = amount;
        this.user = user;
        this.product = product;
    }

    /**
     * Get the cart's ID.
     *
     * @return The cart's ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the cart's ID.
     *
     * @param id The cart's ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the cart's amount.
     *
     * @return The cart's amount.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Set the cart's amount.
     *
     * @param amount The cart's amount.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Get the user associated with the cart.
     *
     * @return The user associated with the cart.
     */
    public UserEntity getUser() {
        return user;
    }

    /**
     * Set the user associated with the cart.
     *
     * @param user The user associated with the cart.
     */
    public void setUser(UserEntity user) {
        this.user = user;
    }

    /**
     * Get the product in the cart.
     *
     * @return The product in the cart.
     */
    public ProductEntity getProduct() {
        return product;
    }

    /**
     * Set the product in the cart.
     *
     * @param product The product in the cart.
     */
    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}