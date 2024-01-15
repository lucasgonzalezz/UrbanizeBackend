package com.ecommerce.urbanize.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String lastName1;

    @Size(min = 3, max = 255)
    private String lastName2;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate birthDate;

    @NotNull
    @Size(min = 9, max = 9, message = "The phone number must have 9 characters")
    @Pattern(regexp = "\\+?[0-9]+", message = "The phone number must contain only digits")
    private int phoneNumber;

    @NotNull
    @NotBlank
    @Size(min = 9, max = 9, message = "The DNI must have 8 characters")
    @Pattern(regexp = "\\d+", message = "The DNI must contain only digits")
    private String dni;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String city;

    @NotNull
    @Min(value = 1000, message = "The postal code must be at least 1000")
    @Max(value = 52999, message = "The postal code must be at most 52999")
    private int postalCode;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String address;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 15)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @NotBlank
    @Size(min = 64, max = 64)
    @Pattern(regexp = "^[a-fA-F0-9]+$", message = "Password must be hexadecimal")
    private String password = "e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e";

    private Boolean role = false;

    @OneToMany(mappedBy = "user", fetch = jakarta.persistence.FetchType.LAZY)
    private List<OrderEntity> orders;

    @OneToMany(mappedBy = "user", fetch = jakarta.persistence.FetchType.LAZY)
    private List<RatingEntity> ratings;

    @OneToMany(mappedBy = "user", fetch = jakarta.persistence.FetchType.LAZY)
    private List<CartEntity> carts;

    public UserEntity() {
        orders = new ArrayList<>();
        ratings = new ArrayList<>();
        carts = new ArrayList<>();
    }

    public UserEntity(Long id, String name, String lastName1, String lastName2, LocalDate birthDate, int phoneNumber,
            String dni,
            int postalCode, String city, String address, String email, String username, String password,
            Boolean role) {
        this.id = id;
        this.name = name;
        this.lastName1 = lastName1;
        this.lastName2 = lastName2;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.dni = dni;
        this.city = city;
        this.postalCode = postalCode;
        this.address = address;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserEntity(String name, String lastName1, String lastName2, LocalDate birthDate, int phoneNumber, String dni,
            int postalCode, String city, String address, String email, String username, String password,
            Boolean role) {
        this.name = name;
        this.lastName1 = lastName1;
        this.lastName2 = lastName2;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.dni = dni;
        this.city = city;
        this.postalCode = postalCode;
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

    public String getLastName1() {
        return lastName1;
    }

    public void setLastName1(String lastName1) {
        this.lastName1 = lastName1;
    }

    public String getLastName2() {
        return lastName2;
    }

    public void setLastName2(String lastName2) {
        this.lastName2 = lastName2;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
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

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public List<RatingEntity> getRatings() {
        return ratings;
    }

    public List<CartEntity> getCarts() {
        return carts;
    }

}