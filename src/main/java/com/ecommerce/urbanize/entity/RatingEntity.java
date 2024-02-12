package com.ecommerce.urbanize.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
@Table(name = "rating")
public class RatingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 255)
    private String title;

    @Size(min = 3, max = 255)
    private String description;

    @Lob
    private byte[] image;

    private int punctuation;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    /**
     * Default constructor.
     */
    public RatingEntity() {
    }

    /**
     * Constructor with parameters for full entity initialization.
     *
     * @param id          The rating's ID.
     * @param title       The rating's title.
     * @param description The rating's description.
     * @param image       The rating's image.
     * @param punctuation The rating's punctuation.
     * @param date        The rating's date.
     * @param user        The user associated with the rating.
     * @param product     The product associated with the rating.
     */
    public RatingEntity(Long id, String title, String description, byte[] image, int punctuation, LocalDate date,
            UserEntity user, ProductEntity product) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.punctuation = punctuation;
        this.date = date;
        this.user = user;
        this.product = product;
    }

    /**
     * Constructor with parameters for full entity initialization.
     *
     * @param title       The rating's title.
     * @param description The rating's description.
     * @param punctuation The rating's punctuation.
     * @param date        The rating's date.
     * @param user        The user associated with the rating.
     * @param product     The product associated with the rating.
     */
    public RatingEntity(String title, String description, int punctuation, LocalDate date,
            UserEntity user, ProductEntity product) {
        this.title = title;
        this.description = description;
        this.punctuation = punctuation;
        this.date = date;
        this.user = user;
        this.product = product;
    }

    /**
     * Constructor with parameters for partial entity initialization.
     *
     * @param title       The rating's title.
     * @param description The rating's description.
     * @param image       The rating's image.
     * @param punctuation The rating's punctuation.
     * @param date        The rating's date.
     * @param user        The user associated with the rating.
     * @param product     The product associated with the rating.
     */
    public RatingEntity(String title, String description, byte[] image, int punctuation, LocalDate date,
            UserEntity user, ProductEntity product) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.punctuation = punctuation;
        this.date = date;
        this.user = user;
        this.product = product;
    }

    /**
     * Constructor with parameters for minimal entity initialization.
     *
     * @param title       The rating's title.
     * @param description The rating's description.
     * @param image       The rating's image.
     * @param punctuation The rating's punctuation.
     * @param date        The rating's date.
     */
    public RatingEntity(String title, String description, byte[] image, int punctuation, LocalDate date) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.punctuation = punctuation;
        this.date = date;
    }

    /**
     * Constructor with parameters for minimal entity initialization.
     *
     * @param title       The rating's title.
     * @param description The rating's description.
     * @param image       The rating's image.
     * @param punctuation The rating's punctuation.
     * @param date        The rating's date.
     */
    public RatingEntity(String title, String description, int punctuation, LocalDate date) {
        this.title = title;
        this.description = description;
        this.punctuation = punctuation;
        this.date = date;
    }

    /**
     * Get the rating's ID.
     *
     * @return The rating's ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the rating's ID.
     *
     * @param id The rating's ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the rating's title.
     *
     * @return The rating's title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the rating's title.
     *
     * @param title The rating's title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the rating's description.
     *
     * @return The rating's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the rating's description.
     *
     * @param description The rating's description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the rating's punctuation.
     *
     * @return The rating's punctuation.
     */
    public int getPunctuation() {
        return punctuation;
    }

    /**
     * Set the rating's punctuation.
     *
     * @param punctuation The rating's punctuation.
     */
    public void setPunctuation(int punctuation) {
        this.punctuation = punctuation;
    }

    /**
     * Get the rating's image.
     *
     * @return The rating's image.
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * Set the rating's image.
     *
     * @param image The rating's image.
     */
    public void setImage(byte[] image) {
        this.image = image;
    }

    /**
     * Get the rating's date.
     *
     * @return The rating's date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Set the rating's date.
     *
     * @param date The rating's date.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Get the user associated with the rating.
     *
     * @return The user associated with the rating.
     */
    public UserEntity getUser() {
        return user;
    }

    /**
     * Set the user associated with the rating.
     *
     * @param user The user associated with the rating.
     */
    public void setUser(UserEntity user) {
        this.user = user;
    }

    /**
     * Get the product associated with the rating.
     *
     * @return The product associated with the rating.
     */
    public ProductEntity getProduct() {
        return product;
    }

    /**
     * Set the product associated with the rating.
     *
     * @param product The product associated with the rating.
     */
    public void setProduct(ProductEntity product) {
        this.product = product;
    }

}