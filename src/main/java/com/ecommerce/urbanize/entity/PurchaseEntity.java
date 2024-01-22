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
    private LocalDate purchase_date;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate delivery_date;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String status;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String purchase_code;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @NotNull
    private int num_bill;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date_bill;

    public PurchaseEntity() {
    }

    public PurchaseEntity(Long id, LocalDate purchaseDate, LocalDate deliveryDate, String status, String purchaseCode,
            UserEntity user, int numBill, LocalDate dateBill) {
        this.id = id;
        this.purchase_date = purchaseDate;
        this.delivery_date = deliveryDate;
        this.status = status;
        this.purchase_code = purchaseCode;
        this.user = user;
        this.num_bill = numBill;
        this.date_bill = dateBill;
    }

    public PurchaseEntity(LocalDate purchaseDate, LocalDate deliveryDate, String status, String purchaseCode,
            UserEntity user, int numBill, LocalDate dateBill) {
        this.purchase_date = purchaseDate;
        this.delivery_date = deliveryDate;
        this.status = status;
        this.purchase_code = purchaseCode;
        this.user = user;
        this.num_bill = numBill;
        this.date_bill = dateBill;
    }

    public PurchaseEntity(LocalDate purchaseDate, LocalDate deliveryDate, String status, String purchaseCode,
            int numBill, LocalDate dateBill) {
        this.purchase_date = purchaseDate;
        this.delivery_date = deliveryDate;
        this.status = status;
        this.purchase_code = purchaseCode;
        this.num_bill = numBill;
        this.date_bill = dateBill;
    }

    public PurchaseEntity(LocalDate purchaseDate, LocalDate deliveryDate, String status, String purchaseCode) {
        this.purchase_date = purchaseDate;
        this.delivery_date = deliveryDate;
        this.status = status;
        this.purchase_code = purchaseCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getPurchaseDate() {
        return purchase_date;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchase_date = purchaseDate;
    }

    public LocalDate getDeliveryDate() {
        return delivery_date;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.delivery_date = deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPurchaseCode() {
        return purchase_code;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchase_code = purchaseCode;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public int getNumBill() {
        return num_bill;
    }

    public void setNumBill(int numBill) {
        this.num_bill = numBill;
    }

    public LocalDate getDateBill() {
        return date_bill;
    }

    public void setDateBill(LocalDate dateBill) {
        this.date_bill = dateBill;
    }

}