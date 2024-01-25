package com.ecommerce.urbanize.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "purchase_detail")
public class PurchaseDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int amount;

    @NotNull
    private int price;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private PurchaseEntity purchase;

    /**
     * Default constructor.
     */
    public PurchaseDetailEntity() {
    }

    /**
     * Constructor with parameters for full entity initialization.
     *
     * @param id       The purchase detail's ID.
     * @param amount   The purchase detail's amount.
     * @param price    The purchase detail's price.
     * @param product  The product associated with the purchase detail.
     * @param purchase The purchase associated with the purchase detail.
     */
    public PurchaseDetailEntity(Long id, int amount, int price, ProductEntity product, PurchaseEntity purchase) {
        this.id = id;
        this.amount = amount;
        this.price = price;
        this.product = product;
        this.purchase = purchase;
    }

    /**
     * Constructor with parameters for partial entity initialization.
     *
     * @param amount   The purchase detail's amount.
     * @param price    The purchase detail's price.
     * @param product  The product associated with the purchase detail.
     * @param purchase The purchase associated with the purchase detail.
     */
    public PurchaseDetailEntity(int amount, int price, ProductEntity product, PurchaseEntity purchase) {
        this.amount = amount;
        this.price = price;
        this.product = product;
        this.purchase = purchase;
    }

    /**
     * Get the purchase detail's ID.
     *
     * @return The purchase detail's ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the purchase detail's ID.
     *
     * @param id The purchase detail's ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the purchase detail's amount.
     *
     * @return The purchase detail's amount.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Set the purchase detail's amount.
     *
     * @param amount The purchase detail's amount.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Get the purchase detail's price.
     *
     * @return The purchase detail's price.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Set the purchase detail's price.
     *
     * @param price The purchase detail's price.
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Get the product associated with the purchase detail.
     *
     * @return The product associated with the purchase detail.
     */
    public ProductEntity getProduct() {
        return product;
    }

    /**
     * Set the product associated with the purchase detail.
     *
     * @param product The product associated with the purchase detail.
     */
    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    /**
     * Get the purchase associated with the purchase detail.
     *
     * @return The purchase associated with the purchase detail.
     */
    public PurchaseEntity getPurchase() {
        return purchase;
    }

    /**
     * Set the purchase associated with the purchase detail.
     *
     * @param purchase The purchase associated with the purchase detail.
     */
    public void setPurchase(PurchaseEntity purchase) {
        this.purchase = purchase;
    }
}