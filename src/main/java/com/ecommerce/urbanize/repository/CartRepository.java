package com.ecommerce.urbanize.repository;

import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ecommerce.urbanize.entity.CartEntity;

// Interface for managing shopping cart data in the database
public interface CartRepository extends JpaRepository<CartEntity, Long> {

    // Get a page of cart items for a specific user
    Page<CartEntity> findByIdUser(Long idUser, Pageable pageable);

    // Find a specific item in the cart based on user and product IDs
    Optional<CartEntity> findByIdUserAndIdProduct(Long idUser, Long idProduct);

    // Remove all cart items for a specific user
    @Query(value = "DELETE FROM cart WHERE idUser = ?1", nativeQuery = true)
    void deleteByIdUser(Long idUser);

    // Reset the auto-increment counter for the cart table
    @Modifying
    @Query(value = "ALTER TABLE cart AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}