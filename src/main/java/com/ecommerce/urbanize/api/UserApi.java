package com.ecommerce.urbanize.api;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.ecommerce.urbanize.entity.UserEntity;
import com.ecommerce.urbanize.service.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserApi {

    @Autowired
    UserService oUserService;

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oUserService.get(id));
    }

    // Get user by username
    @GetMapping("/byUsername/{username}")
    public ResponseEntity<UserEntity> get(@PathVariable("username") String username) {
        return ResponseEntity.ok(oUserService.getByUsername(username));
    }

    // Get a random user
    @GetMapping("/random")
    public ResponseEntity<UserEntity> getOneRandom() {
        return ResponseEntity.ok(oUserService.getOneRandom());
    }

    // Create a new user
    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody UserEntity oUserEntity) {
        return ResponseEntity.ok(oUserService.create(oUserEntity));
    }

    // Update an existing user
    @PutMapping("")
    public ResponseEntity<UserEntity> update(@RequestBody UserEntity oUserEntity) {
        return ResponseEntity.ok(oUserService.update(oUserEntity));
    }

    // Delete a user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oUserService.delete(id));
    }

    // Get a cantity of users using pagination
    @GetMapping("")
    public ResponseEntity<Page<UserEntity>> getPage(
            @PageableDefault(size = 30, sort = { "id" }, direction = Sort.Direction.ASC) Pageable oPageable) {
        return ResponseEntity.ok(oUserService.getPage(oPageable));
    }

    // Populate
    @GetMapping("/populate/{amount}")
    public ResponseEntity<Long> populate(@PathVariable("amount") Integer amount) {
        return ResponseEntity.ok(oUserService.populate(amount));
    }

    // Empty
    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(oUserService.empty());
    }

    // Get users with the most orders
    @GetMapping("/mostPurchases")
    public ResponseEntity<Page<UserEntity>> getMostPurchases(Pageable oPageable) {
        return ResponseEntity.ok(oUserService.getUsersWithMostPurchases(oPageable));
    }

    // Get users with the fewest orders
    @GetMapping("/fewestPurchases")
    public ResponseEntity<Page<UserEntity>> getFewestPurchases(Pageable oPageable) {
        return ResponseEntity.ok(oUserService.getUsersWithFewestPurchases(oPageable));
    }

    // Get users with the most ratings
    @GetMapping("/mostRatings")
    public ResponseEntity<Page<UserEntity>> getMostRatings(Pageable oPageable) {
        return ResponseEntity.ok(oUserService.getUsersWithMostRatings(oPageable));
    }

}