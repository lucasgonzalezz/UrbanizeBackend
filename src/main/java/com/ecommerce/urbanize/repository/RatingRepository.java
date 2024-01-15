// This is a repository interface for handling database operations related to ratings in an e-commerce application.
package com.ecommerce.urbanize.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ecommerce.urbanize.entity.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RatingRepository extends JpaRepository<RatingEntity, Long> {

    // Find all ratings for a specific product
    Page<RatingEntity> findByIdProduct(Long idProduct, Pageable pageable);

    // Find all ratings by a specific user
    Page<RatingEntity> findByIdUser(Long idUser, Pageable pageable);

    // Find a rating for a specific product and user
    Optional<RatingEntity> findByIdProductAndIdUser(Long idProduct, Long idUser);

    // Get the average rating of a product
    @Query(value = "SELECT AVG(punctuation) FROM rating WHERE idProduct = ?1", nativeQuery = true)
    Double getAverageRating(Long idProduct);

    // Get ratings sorted by lowest punctuation for a product
    @Query(value = "SELECT * FROM rating WHERE idProduct = ?1 ORDER BY punctuation DESC", nativeQuery = true)
    Page<RatingEntity> getRatingByLowestPunctuation(Long idProduct, Pageable pageable);

    // Get ratings sorted by highest punctuation for a product
    @Query(value = "SELECT * FROM rating WHERE idProduct = ?1 ORDER BY punctuation ASC", nativeQuery = true)
    Page<RatingEntity> getRatingByHighestPunctuation(Long idProduct, Pageable pageable);

    // Get ratings sorted by newest for a product
    @Query(value = "SELECT * FROM rating WHERE idProduct = ?1 ORDER BY date DESC", nativeQuery = true)
    Page<RatingEntity> getRatingByNewest(Long idProduct, Pageable pageable);

    // Get ratings sorted by oldest for a product
    @Query(value = "SELECT * FROM rating WHERE idProduct = ?1 ORDER BY date ASC", nativeQuery = true)
    Page<RatingEntity> getRatingByOldest(Long idProduct, Pageable pageable);

    // Get ratings sorted by lowest punctuation for a user
    @Query(value = "SELECT * FROM rating WHERE idUser = ?1 ORDER BY punctuation DESC", nativeQuery = true)
    Page<RatingEntity> getRatingByLowestPunctuationOfUsers(Long idUser, Pageable pageable);

    // Get ratings sorted by highest punctuation for a user
    @Query(value = "SELECT * FROM rating WHERE idUser = ?1 ORDER BY punctuation ASC", nativeQuery = true)
    Page<RatingEntity> getRatingByHighestPunctuationOfUsers(Long idUser, Pageable pageable);

    // Get ratings sorted by newest for a user
    @Query(value = "SELECT * FROM rating WHERE idUser = ?1 ORDER BY date DESC", nativeQuery = true)
    Page<RatingEntity> getRatingByNewestOfUsers(Long idUser, Pageable pageable);

    // Get ratings sorted by oldest for a user
    @Query(value = "SELECT * FROM rating WHERE idUser = ?1 ORDER BY date ASC", nativeQuery = true)
    Page<RatingEntity> getRatingByOldestOfUsers(Long idUser, Pageable pageable);

    // Reset the auto-increment value of the rating table
    @Modifying
    @Query(value = "ALTER TABLE rating AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}