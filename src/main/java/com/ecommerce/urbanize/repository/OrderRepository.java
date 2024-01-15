// This is a repository interface for managing database operations related to orders in an e-commerce application.
package com.ecommerce.urbanize.repository;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.ecommerce.urbanize.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    // Find orders by user ID
    Page<OrderEntity> findByIdUser(Long idUser, Pageable pageable);

    // Find orders, ordered by the newest first
    @Query(value = "SELECT * FROM order ORDER BY orderDate DESC", nativeQuery = true)
    Page<OrderEntity> findByNewestOrder(Pageable pageable);

    // Find orders, ordered by the oldest first
    @Query(value = "SELECT * FROM order ORDER BY orderDate ASC", nativeQuery = true)
    Page<OrderEntity> findByOldestOrder(Pageable pageable);

    // Find orders by order code (using LIKE)
    @Query(value = "SELECT * FROM order WHERE orderCode LIKE %?1%", nativeQuery = true)
    Page<OrderEntity> findByOrderCode(String orderCode, Pageable pageable);

    // Reset the auto-increment value of the order table
    @Modifying
    @Query(value = "ALTER TABLE order AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}