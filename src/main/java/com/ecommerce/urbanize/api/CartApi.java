package com.ecommerce.urbanize.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.data.web.PageableDefault;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.urbanize.entity.CartEntity;
import com.ecommerce.urbanize.service.CartService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/cart")
public class CartApi {

    @Autowired
    CartService oCartService;

    // Get cart by ID
    @GetMapping("/{id}")
    public ResponseEntity<CartEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oCartService.get(id));
    }

    // Get cart by user ID
    @GetMapping("/byUser/{user_id}")
    public ResponseEntity<List<CartEntity>> getCartByUser(@PathVariable("user_id") Long user_id) {
        return ResponseEntity.ok(oCartService.getCartByUser(user_id));
    }

    // Get cart by user ID and product ID
    @GetMapping("/byUserAndProduct/{user_id}/{product_id}")
    public ResponseEntity<CartEntity> getByUserAndProduct(@PathVariable("user_id") Long user_id,
            @PathVariable("product_id") Long product_id) {
        return ResponseEntity.ok(oCartService.getByUserAndProduct(user_id, product_id));
    }

    // Create a new cart
    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody CartEntity oCartEntity) {
        return ResponseEntity.ok(oCartService.create(oCartEntity));
    }

    // Update an existing cart
    @PutMapping("")
    public ResponseEntity<CartEntity> update(@RequestBody CartEntity oCartEntity) {
        return ResponseEntity.ok(oCartService.update(oCartEntity));
    }

    // Delete a cart by ID
    @DeleteMapping("{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oCartService.delete(id));
    }

    // Get page of carts
    @GetMapping("")
    public ResponseEntity<Page<CartEntity>> getPage(
            @PageableDefault(size = 30, sort = { "id" }, direction = Sort.Direction.ASC) Pageable oPageable) {
        return ResponseEntity.ok(oCartService.getPage(oPageable));
    }

    // Poulate database with random carts
    @PostMapping("/populate/{amount}")
    public ResponseEntity<Long> populate(@PathVariable("amount") Integer amount) {
        return ResponseEntity.ok(oCartService.populate(amount));
    }

    // Empty the cart table
    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(oCartService.empty());
    }

    // Delete all carts for a specific user
    @DeleteMapping("/user/{user_id}")
    public ResponseEntity<Long> deleteCarritoByUsuario(@PathVariable("usuarioId") Long usuarioId) {
        oCartService.deleteByUserId(usuarioId);
        return ResponseEntity.ok(usuarioId);
    }

    // Calculate the cost of a specific cart
    @GetMapping("/cost/{cart_id}")
    public ResponseEntity<Double> calculateCartCost(@PathVariable("cart_id") Long cart_id) {
        return ResponseEntity.ok(oCartService.calculateCartCost(cart_id));
    }

    // Calculate the total cost of all carts for a specific user
    @GetMapping("/totalCost/{user_id}")
    public ResponseEntity<Double> calculateTotalCartCost(@PathVariable("user_id") Long user_id) {
        return ResponseEntity.ok(oCartService.calculateTotalCartCost(user_id));
    }

    // Get all carts for a specific user
    @GetMapping("/allByUser/{user_id}")
    public ResponseEntity<List<CartEntity>> getAllByUser(@PathVariable("user_id") Long user_id) {
        return ResponseEntity.ok(oCartService.getAllByUser(user_id));

    }
}