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

    // Get purchase details by purchase ID
    @GetMapping("/byPurchase/{purchase_id}")
    public ResponseEntity<Page<PurchaseDetailEntity>> findByPurchaseId(@PathVariable("purchase_id") Long purchase_id,
            @PageableDefault(size = 30, sort = { "id" }, direction = Sort.Direction.ASC) Pageable oPageable) {
        return ResponseEntity.ok(oPurchaseDetailService.findByPurchaseId(purchase_id, oPageable));
    }

    // Get purchase details by product ID
    @GetMapping("/byProduct/{product_id}")
    public ResponseEntity<Page<PurchaseDetailEntity>> findByProductId(@PathVariable("product_id") Long product_id,
            @PageableDefault(size = 30, sort = { "id" }, direction = Sort.Direction.ASC) Pageable oPageable) {
        return ResponseEntity.ok(oPurchaseDetailService.findByProductId(product_id, oPageable));
    }

    // Get purchase details by purchase ID and product ID
    @GetMapping("/byPurchaseAndProduct/{purchase_id}/{product_id}")
    public ResponseEntity<Page<PurchaseDetailEntity>> findByPurchaseIdAndProductId(@PathVariable("purchase_id") Long purchase_id,
            @PathVariable("product_id") Long product_id,
            @PageableDefault(size = 30, sort = { "id" }, direction = Sort.Direction.ASC) Pageable oPageable) {
        return ResponseEntity.ok(oPurchaseDetailService.findByPurchaseIdAndProductId(purchase_id, product_id, oPageable));
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
    
    // Empty the purchase detail table
    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(oPurchaseDetailService.empty());
    }

    // Get purchase details ordered by price in descending purchase
    @GetMapping("/byPriceDesc")
    public ResponseEntity<Page<PurchaseDetailEntity>> findAllByPriceDesc(
            @PageableDefault(size = 30, sort = { "price" }, direction = Sort.Direction.DESC) Pageable oPageable) {
        return ResponseEntity.ok(oPurchaseDetailService.findAllByPriceDesc(oPageable));
    }

    // Get purchase details ordered by price in ascending purchase
    @GetMapping("/byPriceAsc")
    public ResponseEntity<Page<PurchaseDetailEntity>> findAllByPriceAsc(
            @PageableDefault(size = 30, sort = { "price" }, direction = Sort.Direction.ASC) Pageable oPageable) {
        return ResponseEntity.ok(oPurchaseDetailService.findAllByPriceAsc(oPageable));
    }

}