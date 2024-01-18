package com.ecommerce.urbanize.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.urbanize.entity.PurchaseEntity;
import com.ecommerce.urbanize.service.PurchaseService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/order")
public class OrderApi {

    @Autowired 
    PurchaseService oOrderService;

    // Get order by ID
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oOrderService.get(id));
    }

    // Generate order code
    @GetMapping("/generateOrderCode")
    public ResponseEntity<String> generateOrderCode() {
        return ResponseEntity.ok(oOrderService.generateOrderCode());
    }

    // empty the order table
    @GetMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(oOrderService.empty());
    }

}