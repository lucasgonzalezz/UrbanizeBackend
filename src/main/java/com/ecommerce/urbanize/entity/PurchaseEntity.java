package com.ecommerce.urbanize.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

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

    /**
     * Default constructor.
     */
    public PurchaseEntity() {
    }

    /**
     * Constructor with parameters for full entity initialization.
     *
     * @param id           The purchase's ID.
     * @param purchaseDate The purchase's purchase date.
     * @param deliveryDate The purchase's delivery date.
     * @param status       The purchase's status.
     * @param purchaseCode The purchase's purchase code.
     * @param user         The user associated with the purchase.
     * @param numBill      The purchase's bill number.
     * @param dateBill     The purchase's bill date.
     */
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

    /**
     * Constructor with parameters for partial entity initialization.
     *
     * @param purchaseDate The purchase's purchase date.
     * @param deliveryDate The purchase's delivery date.
     * @param status       The purchase's status.
     * @param purchaseCode The purchase's purchase code.
     * @param user         The user associated with the purchase.
     * @param numBill      The purchase's bill number.
     * @param dateBill     The purchase's bill date.
     */
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

    /**
     * Constructor with parameters for partial entity initialization (without user
     * association).
     *
     * @param purchaseDate The purchase's purchase date.
     * @param deliveryDate The purchase's delivery date.
     * @param status       The purchase's status.
     * @param purchaseCode The purchase's purchase code.
     * @param numBill      The purchase's bill number.
     * @param dateBill     The purchase's bill date.
     */
    public PurchaseEntity(LocalDate purchaseDate, LocalDate deliveryDate, String status, String purchaseCode,
            int numBill, LocalDate dateBill) {
        this.purchase_date = purchaseDate;
        this.delivery_date = deliveryDate;
        this.status = status;
        this.purchase_code = purchaseCode;
        this.num_bill = numBill;
        this.date_bill = dateBill;
    }

    /**
     * Constructor with parameters for minimal entity initialization.
     *
     * @param purchaseDate The purchase's purchase date.
     * @param deliveryDate The purchase's delivery date.
     * @param status       The purchase's status.
     * @param purchaseCode The purchase's purchase code.
     */
    public PurchaseEntity(LocalDate purchaseDate, LocalDate deliveryDate, String status, String purchaseCode) {
        this.purchase_date = purchaseDate;
        this.delivery_date = deliveryDate;
        this.status = status;
        this.purchase_code = purchaseCode;
    }

    /**
     * Get the purchase's ID.
     *
     * @return The purchase's ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the purchase's ID.
     *
     * @param id The purchase's ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the purchase's purchase date.
     *
     * @return The purchase's purchase date.
     */
    public LocalDate getPurchaseDate() {
        return purchase_date;
    }

    /**
     * Set the purchase's purchase date.
     *
     * @param purchaseDate The purchase's purchase date.
     */
    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchase_date = purchaseDate;
    }

    /**
     * Get the purchase's delivery date.
     *
     * @return The purchase's delivery date.
     */
    public LocalDate getDeliveryDate() {
        return delivery_date;
    }

    /**
     * Set the purchase's delivery date.
     *
     * @param deliveryDate The purchase's delivery date.
     */
    public void setDeliveryDate(LocalDate deliveryDate) {
        this.delivery_date = deliveryDate;
    }

    /**
     * Get the purchase's status.
     *
     * @return The purchase's status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the purchase's status.
     *
     * @param status The purchase's status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Get the purchase's purchase code.
     *
     * @return The purchase's purchase code.
     */
    public String getPurchaseCode() {
        return purchase_code;
    }

    /**
     * Set the purchase's purchase code.
     *
     * @param purchaseCode The purchase's purchase code.
     */
    public void setPurchaseCode(String purchaseCode) {
        this.purchase_code = purchaseCode;
    }

    /**
     * Get the user associated with the purchase.
     *
     * @return The user associated with the purchase.
     */
    public UserEntity getUser() {
        return user;
    }

    /**
     * Set the user associated with the purchase.
     *
     * @param user The user associated with the purchase.
     */
    public void setUser(UserEntity user) {
        this.user = user;
    }

    /**
     * Get the purchase's bill number.
     *
     * @return The purchase's bill number.
     */
    public int getNumBill() {
        return num_bill;
    }

    /**
     * Set the purchase's bill number.
     *
     * @param numBill The purchase's bill number.
     */
    public void setNumBill(int numBill) {
        this.num_bill = numBill;
    }

    /**
     * Get the purchase's bill date.
     *
     * @return The purchase's bill date.
     */
    public LocalDate getDateBill() {
        return date_bill;
    }

    /**
     * Set the purchase's bill date.
     *
     * @param dateBill The purchase's bill date.
     */
    public void setDateBill(LocalDate dateBill) {
        this.date_bill = dateBill;
    }
}