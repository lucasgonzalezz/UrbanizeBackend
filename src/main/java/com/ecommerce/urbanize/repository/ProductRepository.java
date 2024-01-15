// Import necessary packages
package com.ecommerce.urbanize.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ecommerce.urbanize.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

// Interface definition extending JpaRepository for ProductEntity with Long as ID type
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    // Method to find products by category ID
    Page<ProductEntity> findByIdCategory(Long id, Pageable pageable);

    // Method to find products by size using @query
    @Query(value = "SELECT * FROM product WHERE size = ?1", nativeQuery = true)
    Page<ProductEntity> findBySize(String size, Pageable pageable);

    // Method to reset the auto-increment counter for the "user" table
    @Modifying
    @Query(value = "ALTER TABLE user AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();

}