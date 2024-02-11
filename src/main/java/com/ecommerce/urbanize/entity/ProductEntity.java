package com.ecommerce.urbanize.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    @NotNull
    private int stock;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 3)
    private String size;

    @NotNull
    private int price;

    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @OneToMany(mappedBy = "product", fetch = jakarta.persistence.FetchType.LAZY)
    private List<PurchaseDetailEntity> purchaseDetails;

    @OneToMany(mappedBy = "product", fetch = jakarta.persistence.FetchType.LAZY)
    private List<RatingEntity> ratings;

    @OneToMany(mappedBy = "product", fetch = jakarta.persistence.FetchType.LAZY)
    private List<CartEntity> carts;

    /**
     * Default constructor initializes lists.
     */
    public ProductEntity() {
        purchaseDetails = new ArrayList<>();
        ratings = new ArrayList<>();
        carts = new ArrayList<>();
    }

    /**
     * Constructor with parameters for full entity initialization.
     *
     * @param id       The product's ID.
     * @param name     The product's name.
     * @param stock    The product's stock.
     * @param size     The product's size.
     * @param price    The product's price.
     * @param image    The product's image.
     * @param category The product's category.
     */
    public ProductEntity(Long id, String name, int stock, String size, int price, String image,
            CategoryEntity category) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.size = size;
        this.price = price;
        this.image = image;
        this.category = category;
    }

    /**
     * Constructor with parameters for full entity initialization.
     *
     * @param name     The product's name.
     * @param stock    The product's stock.
     * @param size     The product's size.
     * @param price    The product's price.
     * @param category The product's category.
     */
    public ProductEntity(String name, int stock, String size, int price,
            CategoryEntity category) {
        this.name = name;
        this.stock = stock;
        this.size = size;
        this.price = price;
        this.category = category;
    }

    /**
     * Constructor with parameters for partial entity initialization.
     *
     * @param name     The product's name.
     * @param stock    The product's stock.
     * @param size     The product's size.
     * @param price    The product's price.
     * @param image    The product's image.
     * @param category The product's category.
     */
    public ProductEntity(String name, int stock, String size, int price, String image, CategoryEntity category) {
        this.name = name;
        this.stock = stock;
        this.size = size;
        this.price = price;
        this.image = image;
        this.category = category;
    }

    /**
     * Constructor with parameters for minimal entity initialization.
     *
     * @param name  The product's name.
     * @param stock The product's stock.
     * @param size  The product's size.
     * @param price The product's price.
     * @param image The product's image.
     */
    public ProductEntity(String name, int stock, String size, int price, String image) {
        this.name = name;
        this.stock = stock;
        this.size = size;
        this.price = price;
        this.image = image;
    }

    /**
     * Get the product's ID.
     *
     * @return The product's ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the product's ID.
     *
     * @param id The product's ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the product's name.
     *
     * @return The product's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the product's name.
     *
     * @param name The product's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the product's stock.
     *
     * @return The product's stock.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Set the product's stock.
     *
     * @param stock The product's stock.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Get the product's size.
     *
     * @return The product's size.
     */
    public String getSize() {
        return size;
    }

    /**
     * Set the product's size.
     *
     * @param size The product's size.
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Get the product's price.
     *
     * @return The product's price.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Set the product's price.
     *
     * @param price The product's price.
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Get the product's image.
     *
     * @return The product's image.
     */
    public String getImage() {
        return image;
    }

    /**
     * Set the product's image.
     *
     * @param image The product's image.
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Get the product's category.
     *
     * @return The product's category.
     */
    public CategoryEntity getCategory() {
        return category;
    }

    /**
     * Set the product's category.
     *
     * @param category The product's category.
     */
    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

        /**
     * Returns the number of orders associated with this category.
     * Uses the size of the orders collection.
     *
     * @return The number of orders associated with the category.
     */
    public int getPurchaseDetails() {
        return purchaseDetails.size();
    }

    /**
     * Returns the number of ratings associated with this category.
     * Uses the size of the ratings collection.
     *
     * @return The number of ratings associated with the category.
     */
    public int getRatings() {
        return ratings.size();
    }

    /**
     * Returns the number of carts associated with this category.
     * Uses the size of the carts collection.
     *
     * @return The number of carts associated with the category.
     */
    public int getCarts() {
        return carts.size();
    }
}