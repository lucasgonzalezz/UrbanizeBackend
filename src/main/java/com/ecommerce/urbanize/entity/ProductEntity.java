package com.ecommerce.urbanize.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

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
    @JoinColumn(name = "idCategory")
    private CategoryEntity category;

    public ProductEntity() {
    }

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

    public ProductEntity(String name, int stock, String size, int price, byte[] image, CategoryEntity category) {
        this.name = name;
        this.stock = stock;
        this.size = size;
        this.price = price;
        this.image = image;
        this.category = category;
    }

    public ProductEntity(String name, int stock, String size, int price, byte[] image) {
        this.name = name;
        this.stock = stock;
        this.size = size;
        this.price = price;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

}