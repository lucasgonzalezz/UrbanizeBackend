package com.ecommerce.urbanize.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.urbanize.entity.PurchaseEntity;
import com.ecommerce.urbanize.exception.ResourceNotFoundException;
import com.ecommerce.urbanize.repository.OrderRepository;
import com.ecommerce.urbanize.repository.PurchaseDetailRepository;
import jakarta.transaction.Transactional;

@Service
public class OrderService {

    @Autowired
    OrderRepository oOrderRepository;

    @Autowired
    PurchaseDetailRepository oPurchaseDetailRepository;

    @Autowired
    CartService oCartService;

    @Autowired
    UserService oUserService;

    @Autowired
    ProductService oProductService;

    @Autowired
    PurchaseDetailService oPurchaseDetailService;

    // Get order by ID
    public PurchaseEntity get(Long id) {
        return oOrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    // Generate order code
    public String generateOrderCode() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String currentDate = LocalDateTime.now().format(formatter);
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        return currentDate + uuid;
    }

    // Make a single cart order

    // Make purchase of all carts

    // Cancel order

    // Empty the order table
    @Transactional
    public Long empty() {
        oOrderRepository.deleteAll();
        oOrderRepository.resetAutoIncrement();
        oOrderRepository.flush();
        return oOrderRepository.count();
    }

}