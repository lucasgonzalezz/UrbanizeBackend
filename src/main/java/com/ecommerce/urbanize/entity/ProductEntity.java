package com.ecommerce.urbanize.entity;

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

    @Lob
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    /**
     * Default constructor.
     */
    public ProductEntity() {
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
    public ProductEntity(Long id, String name, int stock, String size, int price, byte[] image,
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
    public ProductEntity(String name, int stock, String size, int price, byte[] image, CategoryEntity category) {
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
    public ProductEntity(String name, int stock, String size, int price, byte[] image) {
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
    public byte[] getImage() {
        return image;
    }

    /**
     * Set the product's image.
     *
     * @param image The product's image.
     */
    public void setImage(byte[] image) {
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
}