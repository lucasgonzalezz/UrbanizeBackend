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

import com.ecommerce.urbanize.entity.ProductEntity;
import com.ecommerce.urbanize.service.ProductService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/product")
public class ProductApi {

    @Autowired
    ProductService oProductService;

    // Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oProductService.get(id));
    }

    // Get a random product
    @GetMapping("/random")
    public ResponseEntity<ProductEntity> getOneRandom() {
        return ResponseEntity.ok(oProductService.getOneRandom());
    }

    // Create a new product
    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody ProductEntity oProductEntity) {
        return ResponseEntity.ok(oProductService.create(oProductEntity));
    }

    // Update an existing product
    @PutMapping("")
    public ResponseEntity<ProductEntity> update(@RequestBody ProductEntity oProductEntity) {
        return ResponseEntity.ok(oProductService.update(oProductEntity));
    }

    // Delete a product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oProductService.delete(id));
    }

    // Populate

    // Empty
    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(oProductService.empty());
    }

    // Get products by category ID
    @GetMapping("/byCategory/{id}")
    public ResponseEntity<Iterable<ProductEntity>> getByCategory(@PathVariable("id") Long id, Pageable oPageable) {
        return ResponseEntity.ok(oProductService.getByCategory(id, oPageable));
    }

    // Get products by size
    @GetMapping("/bySize/{size}")
    public ResponseEntity<Iterable<ProductEntity>> getBySize(@PathVariable("size") String size, Pageable oPageable) {
        return ResponseEntity.ok(oProductService.getBySize(size, oPageable));
    }

    // Get product by stock
    @GetMapping("/byStock/{stock}")
    public ResponseEntity<Iterable<ProductEntity>> getByStock(@PathVariable("stock") String stock, Pageable oPageable) {
        return ResponseEntity.ok(oProductService.getByStockDesc(oPageable));
    }

    // Get products by price
    @GetMapping("/byPrice/{price}")
    public ResponseEntity<Iterable<ProductEntity>> getByPrice(@PathVariable("price") String price, Pageable oPageable) {
        return ResponseEntity.ok(oProductService.getByPriceDesc(oPageable));
    }

    // Get products by price and category descending
    @GetMapping("/byPriceAndCategory/{category_id}")
    public ResponseEntity<Iterable<ProductEntity>> getByPriceAndCategory(@PathVariable("price") String price,
            @PathVariable("category_id") Long category_id, Pageable oPageable) {
        return ResponseEntity.ok(oProductService.getByPriceDescAndIdCategory(category_id, oPageable));
    }

}