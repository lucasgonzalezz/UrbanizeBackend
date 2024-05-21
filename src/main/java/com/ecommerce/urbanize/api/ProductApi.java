package com.ecommerce.urbanize.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    // Actualizar el stock del producto por ID
    @PutMapping("/{id}/stock")
    public ResponseEntity<ProductEntity> updateStock(@PathVariable("id") Long id,
            @RequestBody Map<String, Integer> stockUpdate) {
        try {
            oProductService.updateStock(id, stockUpdate.get("stock"));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Delete a product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oProductService.delete(id));
    }

    // Get a cantity of products using pagination
    @GetMapping("")
    public ResponseEntity<Page<ProductEntity>> getPage(
            Pageable oPageable,
            @RequestParam(name = "filter", required = false) String strFilter,
            @RequestParam(name = "category", required = false) Long category_id) {
        return new ResponseEntity<>(oProductService.getPage(oPageable, category_id, strFilter), HttpStatus.OK);
    }

    // Populate database with random products
    @PostMapping("/populate/{amount}")
    public ResponseEntity<Long> populate(@PathVariable("amount") Integer amount) {
        return ResponseEntity.ok(oProductService.populate(amount));
    }

    // Empty the product table
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

    @GetMapping("/purchased")
    public ResponseEntity<Page<ProductEntity>> getProductsPurchasedByUser(Long userId, Pageable pageable) {
        Page<ProductEntity> products = oProductService.findProductsPurchased(userId, pageable);
        return ResponseEntity.ok(products);
    }

    // Get products most sold
    @GetMapping("/mostSold")
    public ResponseEntity<Page<ProductEntity>> getProductsMostSold(
            @PageableDefault(size = 10, sort = { "id" }, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<ProductEntity> products = oProductService.getProductsMostSold(pageable);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Get products by search text ignoring case
    @GetMapping("/search")
    public ResponseEntity<Page<ProductEntity>> searchProducts(
            @RequestParam String searchText,
            @PageableDefault(size = 10, sort = { "id" }, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<ProductEntity> products = oProductService.getPageBySearchIgnoreCase(searchText, pageable);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Get products by price and category descending
    @GetMapping("/byPriceAndCategory/{category_id}")
    public ResponseEntity<Iterable<ProductEntity>> getByPriceAndCategory(@PathVariable("price") String price,
            @PathVariable("category_id") Long category_id, Pageable oPageable) {
        return ResponseEntity.ok(oProductService.getByPriceDescAndIdCategory(category_id, oPageable));
    }

}