package com.ecommerce.urbanize.api;

import java.util.Optional;

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

import com.ecommerce.urbanize.entity.RatingEntity;
import com.ecommerce.urbanize.service.RatingService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/rating")
public class RatingApi {

    private static final int PAGE_SIZE = 30;

    @Autowired
    RatingService oRatingService;

    @GetMapping("/{id}")
    public ResponseEntity<RatingEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oRatingService.get(id));
    }

    // get a random rating
    @GetMapping("/random")
    public ResponseEntity<RatingEntity> getOneRandom() {
        return ResponseEntity.ok(oRatingService.getOneRandom());
    }

    // create a new rating
    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody RatingEntity oRatingEntity) {
        return ResponseEntity.ok(oRatingService.create(oRatingEntity));
    }

    // update an existing rating
    @PutMapping("")
    public ResponseEntity<RatingEntity> update(@RequestBody RatingEntity oRatingEntity) {
        return ResponseEntity.ok(oRatingService.update(oRatingEntity));
    }

    // delete a rating by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oRatingService.delete(id));
    }

    // get a page of ratings
    @GetMapping("")
    public ResponseEntity<Page<RatingEntity>> getPage(
            @PageableDefault(size = PAGE_SIZE, sort = { "id" }, direction = Sort.Direction.ASC) Pageable oPageable) {
        return ResponseEntity.ok(oRatingService.getPage(oPageable));
    }

    // populate

    // empty
    @GetMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(oRatingService.empty());
    }

    // Get ratings by product ID
    @GetMapping("/byProduct/{id}")
    public ResponseEntity<Page<RatingEntity>> getByProduct(
            @PathVariable("id") @PageableDefault(size = PAGE_SIZE, sort = {
                    "id" }, direction = Sort.Direction.ASC) Long id,
            Pageable oPageable) {
        return ResponseEntity.ok(oRatingService.findByIdProduct(id, oPageable));
    }

    // Get ratings by user ID
    @GetMapping("/byUser/{id}")
    public ResponseEntity<Page<RatingEntity>> getByUser(@PathVariable("id") @PageableDefault(size = PAGE_SIZE, sort = {
            "id" }, direction = Sort.Direction.ASC) Long id, Pageable oPageable) {
        return ResponseEntity.ok(oRatingService.findByIdUser(id, oPageable));
    }

    // Get rating by product ID and user ID
    @GetMapping("/byProductAndUser/{product_id}/{user_id}")
    public ResponseEntity<Optional<RatingEntity>> getByProductAndUser(@PathVariable("product_id") Long product_id,
            @PathVariable("user_id") Long user_id) {
        return ResponseEntity.ok(oRatingService.findByIdProductAndIdUser(product_id, user_id));
    }

    // Get average rating for a product
    @GetMapping("/average/{product_id}")
    public ResponseEntity<Double> getAverageRating(@PathVariable("product_id") Long product_id) {
        return ResponseEntity.ok(oRatingService.getAverageRating(product_id));
    }

    // Get ratings sorted by lowest punctuation for a product
    @GetMapping("/byLowestPunctuation/{product_id}")
    public ResponseEntity<Page<RatingEntity>> getRatingByLowestPunctuation(@PathVariable("product_id") Long product_id,
            Pageable oPageable) {
        return ResponseEntity.ok(oRatingService.getRatingByLowestPunctuation(product_id, oPageable));
    }

    // Get ratings sorted by highest punctuation for a product
    @GetMapping("/byHighestPunctuation/{product_id}")
    public ResponseEntity<Page<RatingEntity>> getRatingByHighestPunctuation(@PathVariable("product_id") Long product_id,
            Pageable oPageable) {
        return ResponseEntity.ok(oRatingService.getRatingByHighestPunctuation(product_id, oPageable));
    }

    // Get ratings sorted by newest for a product
    @GetMapping("/byNewest/{product_id}")
    public ResponseEntity<Page<RatingEntity>> getRatingByNewest(@PathVariable("product_id") Long product_id,
            Pageable oPageable) {
        return ResponseEntity.ok(oRatingService.getRatingByNewest(product_id, oPageable));
    }

    // Get ratings sorted by oldest for a product
    @GetMapping("/byOldest/{product_id}")
    public ResponseEntity<Page<RatingEntity>> getRatingByOldest(@PathVariable("product_id") Long product_id,
            Pageable oPageable) {
        return ResponseEntity.ok(oRatingService.getRatingByOldest(product_id, oPageable));
    }

    // Get ratings by user with lowest punctuation
    @GetMapping("/byLowestPunctuationOfUsers/{user_id}")
    public ResponseEntity<Page<RatingEntity>> getRatingByLowestPunctuationOfUsers(@PathVariable("user_id") Long user_id,
            Pageable oPageable) {
        return ResponseEntity.ok(oRatingService.getRatingByLowestPunctuationOfUsers(user_id, oPageable));
    }

    // Get ratings by user with highest punctuation
    @GetMapping("/byHighestPunctuationOfUsers/{user_id}")
    public ResponseEntity<Page<RatingEntity>> getRatingByHighestPunctuationOfUsers(
            @PathVariable("user_id") Long user_id,
            Pageable oPageable) {
        return ResponseEntity.ok(oRatingService.getRatingByHighestPunctuationOfUsers(user_id, oPageable));
    }

    // Get ratings by user sorted by newest
    @GetMapping("/byNewestOfUsers/{user_id}")
    public ResponseEntity<Page<RatingEntity>> getRatingByNewestOfUsers(@PathVariable("user_id") Long user_id,
            Pageable oPageable) {
        return ResponseEntity.ok(oRatingService.getRatingByNewestOfUsers(user_id, oPageable));
    }

    // Get ratings by user sorted by oldest
    @GetMapping("/byOldestOfUsers/{user_id}")
    public ResponseEntity<Page<RatingEntity>> getRatingByOldestOfUsers(@PathVariable("user_id") Long user_id,
            Pageable oPageable) {
        return ResponseEntity.ok(oRatingService.getRatingByOldestOfUsers(user_id, oPageable));
    }

}