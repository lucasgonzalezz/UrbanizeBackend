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

import com.ecommerce.urbanize.entity.PurchaseDetailEntity;
import com.ecommerce.urbanize.service.PurchaseDetailService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/prchaseDetail")
public class PurchaseDetailApi {

    @Autowired
    PurchaseDetailService oPurchaseDetailService;

    // Get purchase detail by ID
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseDetailEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oPurchaseDetailService.get(id));
    }

    // Get purchase details by order ID
    @GetMapping("/byOrder/{order_id}")
    public ResponseEntity<Page<PurchaseDetailEntity>> findByIdOrder(@PathVariable("order_id") Long order_id,
            @PageableDefault(size = 30, sort = { "id" }, direction = Sort.Direction.ASC) Pageable oPageable) {
        return ResponseEntity.ok(oPurchaseDetailService.findByIdOrder(order_id, oPageable));
    }

    // Get purchase details by product ID
    @GetMapping("/byProduct/{product_id}")
    public ResponseEntity<Page<PurchaseDetailEntity>> findByIdProduct(@PathVariable("product_id") Long product_id,
            @PageableDefault(size = 30, sort = { "id" }, direction = Sort.Direction.ASC) Pageable oPageable) {
        return ResponseEntity.ok(oPurchaseDetailService.findByIdProduct(product_id, oPageable));
    }

    // Get purchase details by order ID and product ID
    @GetMapping("/byOrderAndProduct/{order_id}/{product_id}")
    public ResponseEntity<Page<PurchaseDetailEntity>> findByIdOrderAndIdProduct(@PathVariable("order_id") Long order_id,
            @PathVariable("product_id") Long product_id,
            @PageableDefault(size = 30, sort = { "id" }, direction = Sort.Direction.ASC) Pageable oPageable) {
        return ResponseEntity.ok(oPurchaseDetailService.findByIdOrderAndIdProduct(order_id, product_id, oPageable));
    }

    // Get a random purchase detail
    @GetMapping("/random")
    public ResponseEntity<PurchaseDetailEntity> getOneRandom() {
        return ResponseEntity.ok(oPurchaseDetailService.getOneRandom());
    }

    // Create a new purchase detail
    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody PurchaseDetailEntity oPurchaseDetailEntity) {
        return ResponseEntity.ok(oPurchaseDetailService.create(oPurchaseDetailEntity));
    }

    // Update an existing purchase detail
    @PutMapping("")
    public ResponseEntity<PurchaseDetailEntity> update(@RequestBody PurchaseDetailEntity oPurchaseDetailEntity) {
        return ResponseEntity.ok(oPurchaseDetailService.update(oPurchaseDetailEntity));
    }

    // Delete an existing purchase detail
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oPurchaseDetailService.delete(id));
    }

    // Get all purchase details
    @GetMapping("")
    public ResponseEntity<Page<PurchaseDetailEntity>> getPage(
            @PageableDefault(size = 30, sort = { "id" }, direction = Sort.Direction.ASC) Pageable oPageable) {
        return ResponseEntity.ok(oPurchaseDetailService.getPage(oPageable));
    }

    // populate

    // empty
    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(oPurchaseDetailService.empty());
    }

    // Get purchase details ordered by price in descending order
    @GetMapping("/byPriceDesc")
    public ResponseEntity<Page<PurchaseDetailEntity>> findAllByPriceDesc(
            @PageableDefault(size = 30, sort = { "price" }, direction = Sort.Direction.DESC) Pageable oPageable) {
        return ResponseEntity.ok(oPurchaseDetailService.findAllByPriceDesc(oPageable));
    }

    // Get purchase details ordered by price in ascending order
    @GetMapping("/byPriceAsc")
    public ResponseEntity<Page<PurchaseDetailEntity>> findAllByPriceAsc(
            @PageableDefault(size = 30, sort = { "price" }, direction = Sort.Direction.ASC) Pageable oPageable) {
        return ResponseEntity.ok(oPurchaseDetailService.findAllByPriceAsc(oPageable));
    }

}