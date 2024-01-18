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
@Table(name = "purchase")
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate purchaseDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate deliveryDate;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String status;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String purchaseCode;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private UserEntity user;

    @NotNull
    private int numBill;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateBill;

    public PurchaseEntity() {
    }

    public PurchaseEntity(Long id, LocalDate purchaseDate, LocalDate deliveryDate, String status, String purchaseCode,
            UserEntity user, int numBill, LocalDate dateBill) {
        this.id = id;
        this.purchaseDate = purchaseDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.purchaseCode = purchaseCode;
        this.user = user;
        this.numBill = numBill;
        this.dateBill = dateBill;
    }

    public PurchaseEntity(LocalDate purchaseDate, LocalDate deliveryDate, String status, String purchaseCode,
            UserEntity user, int numBill, LocalDate dateBill) {
        this.purchaseDate = purchaseDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.purchaseCode = purchaseCode;
        this.user = user;
        this.numBill = numBill;
        this.dateBill = dateBill;
    }

    public PurchaseEntity(LocalDate purchaseDate, LocalDate deliveryDate, String status, String purchaseCode,
            int numBill, LocalDate dateBill) {
        this.purchaseDate = purchaseDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.purchaseCode = purchaseCode;
        this.numBill = numBill;
        this.dateBill = dateBill;
    }

    public PurchaseEntity(LocalDate purchaseDate, LocalDate deliveryDate, String status, String purchaseCode) {
        this.purchaseDate = purchaseDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.purchaseCode = purchaseCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
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

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public int getNumBill() {
        return numBill;
    }

    public void setNumBill(int numBill) {
        this.numBill = numBill;
    }

    public LocalDate getDateBill() {
        return dateBill;
    }

    public void setDateBill(LocalDate dateBill) {
        this.dateBill = dateBill;
    }

}