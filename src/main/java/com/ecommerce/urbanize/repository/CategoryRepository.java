package com.ecommerce.urbanize.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.ecommerce.urbanize.entity.CategoryEntity;

// Repository interface for managing CategoryEntity objects in the database
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    // Categories ordered by the quantity of associated products in descending order
    @Query(value = "SELECT * FROM category ORDER BY (SELECT COUNT(*) FROM product WHERE product.idCategory = category.id) DESC", nativeQuery = true)
    Page<CategoryEntity> findByQuantityProduct(Pageable pageable);

    // Reset the auto-increment value for the primary key in the category table
    @Modifying
    @Query(value = "ALTER TABLE category AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}
