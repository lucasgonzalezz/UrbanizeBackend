package com.ecommerce.urbanize.api;

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

import com.ecommerce.urbanize.entity.CategoryEntity;
import com.ecommerce.urbanize.entity.UserEntity;
import com.ecommerce.urbanize.service.CategoryService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/category")
public class CategoryApi {

    @Autowired
    CategoryService oCategoryService;

    // Get category by ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoryEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oCategoryService.get(id));
    }

        // Get a cantity of categories using pagination
    @GetMapping("")
    public ResponseEntity<Page<CategoryEntity>> getPage(
            @PageableDefault(size = 30, sort = { "id" }, direction = Sort.Direction.ASC) Pageable oPageable) {
        return ResponseEntity.ok(oCategoryService.getPage(oPageable));
    }

    // Get a random category
    @GetMapping("/random")
    public ResponseEntity<CategoryEntity> getOneRandom() {
        return ResponseEntity.ok(oCategoryService.getOneRandom());
    }

    // Create a new category
    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody CategoryEntity oCategoryEntity) {
        return ResponseEntity.ok(oCategoryService.create(oCategoryEntity));
    }

    // Update an existing category
    @PutMapping("")
    public ResponseEntity<CategoryEntity> update(@RequestBody CategoryEntity oCategoryEntity) {
        return ResponseEntity.ok(oCategoryService.update(oCategoryEntity));
    }

    // Delete a category by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oCategoryService.delete(id));
    }

    // Populate database with random categories
    @PostMapping("/populate/{amount}")
    public ResponseEntity<Long> populate(@PathVariable("amount") Integer amount) {
        return ResponseEntity.ok(oCategoryService.populate(amount));
    }

    // Empty the category table
    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(oCategoryService.empty());
    }

    // Get category ordered by the quantity of associated products in descending order
    @GetMapping("/quantityProduct")
    public ResponseEntity<?> getPageByQuantityProduct(Pageable oPageable) {
        return ResponseEntity.ok(oCategoryService.getPageByQuantityProduct(oPageable));
    }

}