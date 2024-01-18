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

    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oProductService.get(id));
    }

    // get a random product
    @GetMapping("/random")
    public ResponseEntity<ProductEntity> getOneRandom() {
        return ResponseEntity.ok(oProductService.getOneRandom());
    }

    // create a new product
    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody ProductEntity oProductEntity) {
        return ResponseEntity.ok(oProductService.create(oProductEntity));
    }

    // update an existing product
    @PutMapping("")
    public ResponseEntity<ProductEntity> update(@RequestBody ProductEntity oProductEntity) {
        return ResponseEntity.ok(oProductService.update(oProductEntity));
    }

    // delete a product by ID  
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oProductService.delete(id));
    }

    // populate

    // empty
    @GetMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(oProductService.empty());
    }

    // get products by category ID
    @GetMapping("/byCategory/{id}")
    public ResponseEntity<Iterable<ProductEntity>> getByCategory(@PathVariable("id") Long id, Pageable oPageable) {
        return ResponseEntity.ok(oProductService.getByCategory(id, oPageable));
    }

    // get products by size
    @GetMapping("/bySize/{size}")
    public ResponseEntity<Iterable<ProductEntity>> getBySize(@PathVariable("size") String size, Pageable oPageable) {
        return ResponseEntity.ok(oProductService.getBySize(size, oPageable));
    }

    // get product by stock
    @GetMapping("/byStock/{stock}")
    public ResponseEntity<Iterable<ProductEntity>> getByStock(@PathVariable("stock") String stock, Pageable oPageable) {
        return ResponseEntity.ok(oProductService.getByStockDesc(oPageable));
    }

    // get products by price
    @GetMapping("/byPrice/{price}")
    public ResponseEntity<Iterable<ProductEntity>> getByPrice(@PathVariable("price") String price, Pageable oPageable) {
        return ResponseEntity.ok(oProductService.getByPriceDesc(oPageable));
    }

    // Get products by price and category descending
    @GetMapping("/byPriceAndCategory/{idCategory}")
    public ResponseEntity<Iterable<ProductEntity>> getByPriceAndCategory(@PathVariable("price") String price, @PathVariable("idCategory") Long idCategory, Pageable oPageable) {
        return ResponseEntity.ok(oProductService.getByPriceDescAndIdCategory(idCategory, oPageable));
    }

}