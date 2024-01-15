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
@Table(name = "purchaseDetail")
public class PurchaseDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int amount;

    @NotNull
    private int price;

    @ManyToOne
    @JoinColumn(name = "idOrder")
    private OrderEntity purchase;

    @ManyToOne
    @JoinColumn(name = "idProduct")
    private ProductEntity product;

    public PurchaseDetailEntity() {
    }

    public PurchaseDetailEntity(Long id, int amount, int price, OrderEntity purchase,
            ProductEntity product) {
        this.id = id;
        this.amount = amount;
        this.price = price;
        this.purchase = purchase;
        this.product = product;
    }

    public PurchaseDetailEntity(int amount, int price, OrderEntity purchase,
            ProductEntity product) {
        this.amount = amount;
        this.price = price;
        this.purchase = purchase;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public OrderEntity getPurchase() {
        return purchase;
    }

    public void setPurchase(OrderEntity purchase) {
        this.purchase = purchase;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

}