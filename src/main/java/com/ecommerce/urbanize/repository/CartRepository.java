package com.ecommerce.urbanize.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ecommerce.urbanize.entity.CartEntity;

// Interface for managing shopping cart data in the database
public interface CartRepository extends JpaRepository<CartEntity, Long> {

    // Get a page of carts for a specific user
    List<CartEntity> findByIdUser(Long idUser);

    // Find a specific cart based on user and product IDs
    Optional<CartEntity> findByIdUserAndIdProduct(Long idUser, Long idProduct);

    @Query(value = "SELECT c.amount * c.product.price FROM cart c WHERE c.id = ?1", nativeQuery = true)
    Double calculateCartCost(Long id);

    @Query(value = "SELECT SUM(c.amount * c.product.price) FROM cart c WHERE c.idUser = ?1", nativeQuery = true)
    Double calculateTotalCartCost(Long idUser);

    // Remove all carts for a specific user
    @Query(value = "DELETE FROM cart WHERE idUser = ?1", nativeQuery = true)
    void deleteByIdUser(Long idUser);

    // Find all carts for a specific user
    @Query(value = "SELECT * FROM cart WHERE idUser = ?1", nativeQuery = true)
    List<CartEntity> findAllByIdUser(Long idUser);

    // Reset the auto-increment counter for the cart table
    @Modifying
    @Query(value = "ALTER TABLE cart AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}