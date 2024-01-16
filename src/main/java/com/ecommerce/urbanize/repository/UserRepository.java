// Import necessary packages
package com.ecommerce.urbanize.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import com.ecommerce.urbanize.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

// Interface definition extending JpaRepository for UserEntity with Long as ID type
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // Find a user by username
    Optional<UserEntity> findByUsername(String username);

    // Find a user by both username and password
    Optional<UserEntity> findByUsernameAndPassword(String username, String password);

    // Find users ordered by the count of orders in descending order
    @Query(value = "SELECT u.*, count o(o.id) FROM user u, order o WHERE u.id = o.idUser GROUP BY u.id ORDER BY count(u.id) DESC", nativeQuery = true)
    Page<UserEntity> findUsersByOrderDesc(Pageable pageable);

    // Find users ordered by the count of orders in ascending order
    @Query(value = "SELECT u.*, count o(o.id) FROM user u, order o WHERE u.id = o.idUser GROUP BY u.id ORDER BY count(u.id) ASC", nativeQuery = true)
    Page<UserEntity> findUsersByOrderAsc(Pageable pageable);

    // Find users ordered by the count of ratings in descending order
    @Query(value = "SELECT u.*, count r(r.id) FROM user u, rating r WHERE u.id = r.idUser GROUP BY u.id ORDER BY count(u.id) DESC", nativeQuery = true)
    Page<UserEntity> findUsersByRatingDesc(Pageable pageable);

    // Reset the auto-increment counter for the "user" table
    @Modifying
    @Query(value = "ALTER TABLE user AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();

}