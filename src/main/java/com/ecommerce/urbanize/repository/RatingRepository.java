// This is a repository interface for handling database operations related to ratings in an e-commerce application.
package com.ecommerce.urbanize.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ecommerce.urbanize.entity.RatingEntity;

public interface RatingRepository extends JpaRepository<RatingEntity, Long> {

    // Find all ratings by a specific product
    Page<RatingEntity> findByIdProduct(Long product_id, Pageable pageable);

    // Find all ratings by a specific user
    Page<RatingEntity> findByIdUser(Long user_id, Pageable pageable);

    // Find a rating for a specific product and user
    Optional<RatingEntity> findByIdProductAndIdUser(Long product_id, Long user_id);

    // Get the average rating of a product
    @Query(value = "SELECT AVG(punctuation) FROM rating WHERE product_id = ?1", nativeQuery = true)
    Double getAverageRating(Long product_id);

    // Get ratings sorted by lowest punctuation for a product
    @Query(value = "SELECT * FROM rating WHERE product_id = ?1 ORDER BY punctuation DESC", nativeQuery = true)
    Page<RatingEntity> getRatingByLowestPunctuation(Long product_id, Pageable pageable);

    // Get ratings sorted by highest punctuation for a product
    @Query(value = "SELECT * FROM rating WHERE product_id = ?1 ORDER BY punctuation ASC", nativeQuery = true)
    Page<RatingEntity> getRatingByHighestPunctuation(Long product_id, Pageable pageable);

    // Get ratings sorted by newest for a product
    @Query(value = "SELECT * FROM rating WHERE product_id = ?1 ORDER BY date DESC", nativeQuery = true)
    Page<RatingEntity> getRatingByNewest(Long product_id, Pageable pageable);

    // Get ratings sorted by oldest for a product
    @Query(value = "SELECT * FROM rating WHERE product_id = ?1 ORDER BY date ASC", nativeQuery = true)
    Page<RatingEntity> getRatingByOldest(Long product_id, Pageable pageable);

    // Get ratings sorted by lowest punctuation for a user
    @Query(value = "SELECT * FROM rating WHERE user_id = ?1 ORDER BY punctuation DESC", nativeQuery = true)
    Page<RatingEntity> getRatingByLowestPunctuationOfUsers(Long user_id, Pageable pageable);

    // Get ratings sorted by highest punctuation for a user
    @Query(value = "SELECT * FROM rating WHERE user_id = ?1 ORDER BY punctuation ASC", nativeQuery = true)
    Page<RatingEntity> getRatingByHighestPunctuationOfUsers(Long user_id, Pageable pageable);

    // Get ratings sorted by newest for a user
    @Query(value = "SELECT * FROM rating WHERE user_id = ?1 ORDER BY date DESC", nativeQuery = true)
    Page<RatingEntity> getRatingByNewestOfUsers(Long user_id, Pageable pageable);

    // Get ratings sorted by oldest for a user
    @Query(value = "SELECT * FROM rating WHERE user_id = ?1 ORDER BY date ASC", nativeQuery = true)
    Page<RatingEntity> getRatingByOldestOfUsers(Long user_id, Pageable pageable);

    // Reset the auto-increment value of the rating table
    @Modifying
    @Query(value = "ALTER TABLE rating AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}