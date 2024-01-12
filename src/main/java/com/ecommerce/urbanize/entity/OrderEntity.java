package com.ecommerce.urbanize.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "order")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate orderDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate deliveryDate;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String status;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBill;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private UserEntity user;

    public OrderEntity() {
    }

    public OrderEntity(Long id, LocalDate orderDate, LocalDate deliveryDate, String status, Long idBill,
            UserEntity user) {
        this.id = id;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.idBill = idBill;
        this.user = user;
    }

    public OrderEntity(LocalDate orderDate, LocalDate deliveryDate, String status, Long idBill, UserEntity user) {
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.idBill = idBill;
        this.user = user;
    }

    public OrderEntity(LocalDate orderDate, LocalDate deliveryDate, String status, Long idBill) {
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.idBill = idBill;
    }

    public OrderEntity(LocalDate orderDate, LocalDate deliveryDate, String status) {
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getIdBill() {
        return idBill;
    }

    public void setIdBill(Long idBill) {
        this.idBill = idBill;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

}