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

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oUserService.get(id));
    }

    // get user by username
    @GetMapping("/byUsername/{username}")
    public ResponseEntity<UserEntity> get(@PathVariable("username") String username) {
        return ResponseEntity.ok(oUserService.getByUsername(username));
    }

    // get a random user
    @GetMapping("/random")
    public ResponseEntity<UserEntity> getOneRandom() {
        return ResponseEntity.ok(oUserService.getOneRandom());
    }

    // create a new user
    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody UserEntity oUserEntity) {
        return ResponseEntity.ok(oUserService.create(oUserEntity));
    }

    // update an existing user
    @PutMapping("")
    public ResponseEntity<UserEntity> update(@RequestBody UserEntity oUserEntity) {
        return ResponseEntity.ok(oUserService.update(oUserEntity));
    }

    // delete a user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oUserService.delete(id));
    }

    // get a cantity of users using pagination
    @GetMapping("")
    public ResponseEntity<Page<UserEntity>> getPage(@PageableDefault(size = 30, sort = {"id"}, direction = Sort.Direction.ASC) Pageable oPageable) {
        return ResponseEntity.ok(oUserService.getPage(oPageable));
    }

    // populate
    @GetMapping("/populate/{amount}")
    public ResponseEntity<Long> populate(@PathVariable("amount") Integer amount) {
        return ResponseEntity.ok(oUserService.populate(amount));
    }

    // empty
    @GetMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(oUserService.empty());
    }

    // Get users with the most orders
    @GetMapping("/mostOrders")
    public ResponseEntity<Page<UserEntity>> getMostOrders(Pageable oPageable) {
        return ResponseEntity.ok(oUserService.getUsersWithMostOrders(oPageable));
    }

    // Get users with the fewest orders
    @GetMapping("/fewestOrders")
    public ResponseEntity<Page<UserEntity>> getFewestOrders(Pageable oPageable) {
        return ResponseEntity.ok(oUserService.getUsersWithFewestOrders(oPageable));
    }

    // Get users with the most ratings
    @GetMapping("/mostRatings")
    public ResponseEntity<Page<UserEntity>> getMostRatings(Pageable oPageable) {
        return ResponseEntity.ok(oUserService.getUsersWithMostRatings(oPageable));
    }

}