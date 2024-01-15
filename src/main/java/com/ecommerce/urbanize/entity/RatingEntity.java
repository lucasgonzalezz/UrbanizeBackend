package com.ecommerce.urbanize.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "rating")
public class RatingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String title;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String description;

    @Lob
    private byte[] image;

    @NotNull
    private int punctuation;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "idProduct")
    private ProductEntity product;

    public RatingEntity() {
    }

    public RatingEntity(Long id, String title, String description, byte[] image, int punctuation, LocalDate date,
            UserEntity user,
            ProductEntity product) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.punctuation = punctuation;
        this.date = date;
        this.user = user;
        this.product = product;
    }

    public RatingEntity(String title, String description, byte[] image, int punctuation, LocalDate date,
            UserEntity user,
            ProductEntity product) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.punctuation = punctuation;
        this.date = date;
        this.user = user;
        this.product = product;
    }

    public RatingEntity(String title, String description, byte[] image, int punctuation, LocalDate date) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.punctuation = punctuation;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPunctuation() {
        return punctuation;
    }

    public void setPunctuation(int punctuation) {
        this.punctuation = punctuation;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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