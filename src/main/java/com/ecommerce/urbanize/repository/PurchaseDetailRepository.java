// This is a repository interface for managing database operations related to purchase details in an e-commerce application.
package com.ecommerce.urbanize.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ecommerce.urbanize.entity.PurchaseDetailEntity;

public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetailEntity, Long> {

    // Find purchase details by purchase ID
    Page<PurchaseDetailEntity> findByPurchaseId(Long purchase_id, Pageable pageable);

    // Find purchase details by product ID
    Page<PurchaseDetailEntity> findByProductId(Long product_id, Pageable pageable);

    // Find purchase details by both purchase and product ID
    Page<PurchaseDetailEntity> findByPurchaseIdAndProductId(Long purchase_id, Long product_id, Pageable pageable);

    // Order purchase details by price in descending order
    @Query(value = "SELECT * FROM purchaseDetail ORDER BY price DESC", nativeQuery = true)
    Page<PurchaseDetailEntity> findAllByPriceDesc(Pageable pageable);

    // Order purchase details by price in ascending order
    @Query(value = "SELECT * FROM purchaseDetail ORDER BY price ASC", nativeQuery = true)
    Page<PurchaseDetailEntity> findAllByPriceAsc(Pageable pageable);

    // Reset the auto-increment value of the purchaseDetail table
    @Modifying
    @Query(value = "ALTER TABLE purchaseDetail AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}