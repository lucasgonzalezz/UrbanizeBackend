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

    /**
     * Default constructor initializing lists.
     */
    public UserEntity() {
        purchases = new ArrayList<>();
        ratings = new ArrayList<>();
        carts = new ArrayList<>();
    }

    /**
     * Constructor with all parameters.
     *
     * @param id         The user's ID.
     * @param name       The user's name.
     * @param surname    The user's surname.
     * @param birth_date The user's birth date.
     * @param dni        The user's DNI.
     * @param address    The user's address.
     * @param email      The user's email.
     * @param username   The user's username.
     * @param password   The user's password.
     * @param role       The user's role.
     */
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

    /**
     * Constructor without ID parameter.
     *
     * @param name       The user's name.
     * @param surname    The user's surname.
     * @param birth_date The user's birth date.
     * @param dni        The user's DNI.
     * @param address    The user's address.
     * @param email      The user's email.
     * @param username   The user's username.
     * @param password   The user's password.
     * @param role       The user's role.
     */
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

    /**
     * Constructor for login.
     *
     * @param username The user's username.
     * @param password The user's password.
     */
    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter para el campo 'id'
    public Long getId() {
        return id;
    }

    // Setter para el campo 'id'
    public void setId(Long id) {
        this.id = id;
    }

    // Getter para el campo 'name'
    public String getName() {
        return name;
    }

    // Setter para el campo 'name'
    public void setName(String name) {
        this.name = name;
    }

    // Getter para el campo 'surname'
    public String getSurname() {
        return surname;
    }

    // Setter para el campo 'surname'
    public void setSurname(String surname) {
        this.surname = surname;
    }

    // Getter para el campo 'birth_date'
    public LocalDate getBirth_date() {
        return birth_date;
    }

    // Setter para el campo 'birth_date'
    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    // Getter para el campo 'dni'
    public String getDni() {
        return dni;
    }

    // Setter para el campo 'dni'
    public void setDni(String dni) {
        this.dni = dni;
    }

    // Getter para el campo 'address'
    public String getAddress() {
        return address;
    }

    // Setter para el campo 'address'
    public void setAddress(String address) {
        this.address = address;
    }

    // Getter para el campo 'email'
    public String getEmail() {
        return email;
    }

    // Setter para el campo 'email'
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter para el campo 'username'
    public String getUsername() {
        return username;
    }

    // Setter para el campo 'username'
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter para el campo 'password'
    public String getPassword() {
        return password;
    }

    // Setter para el campo 'password'
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter para el campo 'role'
    public Boolean getRole() {
        return role;
    }

    // Setter para el campo 'role'
    public void setRole(Boolean role) {
        this.role = role;
    }

    // Getter para el número de compras
    public int getPurchases() {
        return purchases.size();
    }

    // Getter para el número de calificaciones
    public int getRatings() {
        return ratings.size();
    }

    // Getter para el número de carritos
    public int getCarts() {
        return carts.size();
    }

    // Setter para el campo 'password' de tipo Object (no implementado)
    public void setPassword(Object password) {
        throw new UnsupportedOperationException("Unimplemented method 'setPassword'");
    }

}