package com.ecommerce.urbanize.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    @OneToMany(mappedBy = "category", fetch = jakarta.persistence.FetchType.LAZY)
    private List<ProductEntity> products;

    /**
     * Default constructor.
     */
    public CategoryEntity() {
        products = new ArrayList<>();
    }

    /**
     * Constructor with parameters for full entity initialization.
     *
     * @param id   The category's ID.
     * @param name The category's name.
     */
    public CategoryEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Constructor with parameters for partial entity initialization.
     *
     * @param name The category's name.
     */
    public CategoryEntity(String name) {
        this.name = name;
    }

    /**
     * Get the category's ID.
     *
     * @return The category's ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the category's ID.
     *
     * @param id The category's ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the category's name.
     *
     * @return The category's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the category's name.
     *
     * @param name The category's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the list of products associated with the category.
     *
     * @return The list of products associated with the category.
     */
    public List<ProductEntity> getProducts() {
        return products;
    }

    /**
     * Set the list of products associated with the category.
     *
     * @param products The list of products associated with the category.
     */
    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}