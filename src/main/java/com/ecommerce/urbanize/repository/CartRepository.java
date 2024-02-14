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
    @Query(value = "SELECT c.amount * p.price as coste_cart FROM cart c, product p WHERE c.product_id = p.id and c.id = ?1", nativeQuery = true)
    Double calculateCartCost(Long id);

    @Query(value = "SELECT c.cantidad * (ca.precio + (ca.precio * ca.iva / 100.0) - (ca.precio * ca.porcentaje_descuento / 100.0)) as coste_carrito FROM carrito c, camiseta ca WHERE c.camiseta_id = ca.id and c.id = ?1", nativeQuery = true)
    Double calcularCosteCarrito(Long id);


    // Calculate the total cost of all carts for a specific user
    @Query(value = "SELECT SUM(c.amount * p.price) as coste_total FROM cart c, product p, user u WHERE c.product_id = p.id AND c.user_id = u.id and c.user_id = ?1", nativeQuery = true)
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