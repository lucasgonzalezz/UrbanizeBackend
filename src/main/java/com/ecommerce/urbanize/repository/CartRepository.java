package com.ecommerce.urbanize.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ecommerce.urbanize.entity.CartEntity;

// Interface for managing shopping cart data in the database
public interface CartRepository extends JpaRepository<CartEntity, Long> {

    // Get a page of carts for a specific user
    Page<CartEntity> findByUserId(Long user_id, Pageable pageable);

    // Find a specific cart based on user and product IDs
    Optional<CartEntity> findByUserIdAndProductId(Long user_id, Long product_id);

    // Calculate the cost of a specific cart
    @Query(value = "SELECT c.amount * c.product.price FROM cart c WHERE c.id = ?1", nativeQuery = true)
    Double calculateCartCost(Long id);

    // Calculate the total cost of all carts for a specific user
    @Query(value = "SELECT SUM(c.amount * c.product.price) FROM cart c WHERE c.user_id = ?1", nativeQuery = true)
    Double calculateTotalCartCost(Long user_id);

    // Remove all carts for a specific user
    @Modifying
    @Query(value = "DELETE FROM cart WHERE user_id = ?1", nativeQuery = true)
    void deleteByUserId(Long user_id);

    // Find all carts for a specific user
    @Query(value = "SELECT * FROM cart WHERE user_id = ?1", nativeQuery = true)
    List<CartEntity> findAllByIdUser(Long user_id);

    // Reset the auto-increment counter for the cart table
    @Modifying
    @Query(value = "ALTER TABLE cart AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}