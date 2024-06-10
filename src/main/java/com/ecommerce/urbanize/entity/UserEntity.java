package com.ecommerce.urbanize.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 255)
    private String name;

    @Size(min = 3, max = 255)
    private String surname;

    private LocalDate birth_date;

    @Size(min = 9, max = 9, message = "DNI must have 8 numbers followed by a letter")
    @Pattern(regexp = "\\d{8}[a-zA-Z]", message = "DNI must have 8 numbers followed by a letter")
    private String dni;

    @Size(min = 3, max = 255)
    private String address;

    @Size(min = 3, max = 255)
    private String email;

    @Size(min = 6, max = 15)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank
    @Size(min = 64, max = 128)
    @Pattern(regexp = "^[a-fA-F0-9]+$", message = "Password must be hexadecimal")
    private String password;

    private Boolean role = false;

    @OneToMany(mappedBy = "user", fetch = jakarta.persistence.FetchType.LAZY)
    private List<PurchaseEntity> purchases;

    @OneToMany(mappedBy = "user", fetch = jakarta.persistence.FetchType.LAZY)
    private List<RatingEntity> ratings;

    @OneToMany(mappedBy = "user", fetch = jakarta.persistence.FetchType.LAZY)
    private List<CartEntity> carts;

    public UserEntity() {
        purchases = new ArrayList<>();
        ratings = new ArrayList<>();
        carts = new ArrayList<>();
    }

    public UserEntity(Long id, String name, String surname, LocalDate birth_date, String dni, String address,
            String email, String username, String password, Boolean role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birth_date = birth_date;
        this.dni = dni;
        this.address = address;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserEntity(String name, String surname, LocalDate birth_date, String dni, String address, String email,
            String username, String password, Boolean role) {
        this.name = name;
        this.surname = surname;
        this.birth_date = birth_date;
        this.dni = dni;
        this.address = address;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRole() {
        return role;
    }

    public void setRole(Boolean role) {
        this.role = role;
    }

    public int getPurchases() {
        return purchases.size();
    }

    public int getRatings() {
        return ratings.size();
    }

    public int getCarts() {
        return carts.size();
    }

    public void setContrasenya(Object contrasenya) {
        throw new UnsupportedOperationException("Unimplemented method 'setContrasenya'");

    }
}