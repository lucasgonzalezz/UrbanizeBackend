package com.ecommerce.urbanize.repository;

import java.util.Optional;
import com.ecommerce.urbanize.entity.UserEntity;

import org.hibernate.query.Page;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    //make a query to filter users by number of products above the one that has the most products
    @Query(value = "SELECT * FROM user u WHERE u.id IN (SELECT user_id FROM user_product GROUP BY user_id HAVING COUNT(*) >= (SELECT COUNT(*) FROM user_product GROUP BY user_id ORDER BY COUNT(*) DESC LIMIT 1))", nativeQuery = true)
    Page<UserEntity> findUsersWithMostProducts(Pageable pageable);
    

}
