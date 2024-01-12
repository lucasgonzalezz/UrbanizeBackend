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
    private int quantity;

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

    public PurchaseDetailEntity(Long id, int quantity, int price, OrderEntity purchase,
            ProductEntity product) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.purchase = purchase;
        this.product = product;
    }

    public PurchaseDetailEntity(int quantity, int price, OrderEntity purchase,
            ProductEntity product) {
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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