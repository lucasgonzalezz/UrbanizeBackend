package com.ecommerce.urbanize.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
import com.ecommerce.urbanize.service.CategoryService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/category")
public class CategoryApi {

    @Autowired
    CategoryService oCategoryService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oCategoryService.get(id));
    }

    // get a random category
    @GetMapping("/random")
    public ResponseEntity<CategoryEntity> getOneRandom() {
        return ResponseEntity.ok(oCategoryService.getOneRandom());
    }

    // create a new category
    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody CategoryEntity oCategoryEntity) {
        return ResponseEntity.ok(oCategoryService.create(oCategoryEntity));
    }

    // update an existing category
    @PutMapping("")
    public ResponseEntity<CategoryEntity> update(@RequestBody CategoryEntity oCategoryEntity) {
        return ResponseEntity.ok(oCategoryService.update(oCategoryEntity));
    }

    // delete a category by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oCategoryService.delete(id));
    }

    // populate

    // empty
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
