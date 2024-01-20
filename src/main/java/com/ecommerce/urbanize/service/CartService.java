package com.ecommerce.urbanize.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.urbanize.entity.CartEntity;
import com.ecommerce.urbanize.entity.UserEntity;
import com.ecommerce.urbanize.entity.ProductEntity;
import com.ecommerce.urbanize.exception.ResourceNotFoundException;
import com.ecommerce.urbanize.repository.CartRepository;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.transaction.Transactional;

@Service
public class CartService {

    @Autowired
    CartRepository oCartRepository;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    ProductService oProductService;

    @Autowired
    UserService oUserService;

    // Get cart by ID
    public CartEntity get(Long id) {
        return oCartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
    }

    // Get cart by user ID
    public List<CartEntity> getCartByUser(Long user_id) {
        return oCartRepository.findByIdUser(user_id);
    }

    // Get cart by user ID and product ID
    public CartEntity getByUserAndProduct(Long user_id, Long product_id) {
        return oCartRepository.findByIdUserAndIdProduct(user_id, product_id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
    }

    // Get page of carts
    public Page<CartEntity> getPage(Pageable oPageable) {
        return oCartRepository.findAll(oPageable);
    }

    // Create a new cart
    public Long create(CartEntity oCartEntity) {
        UserEntity oUserEntity = oUserService.get(oCartEntity.getUser().getId());
        ProductEntity oProductEntity = oProductService.get(oCartEntity.getProduct().getId());

        Optional<CartEntity> cartFromDatabase = oCartRepository.findByIdUserAndIdProduct(oUserEntity.getId(),
                oProductEntity.getId());
        if (cartFromDatabase.isPresent()) {
            CartEntity cart = cartFromDatabase.get();
            cart.setAmount(cart.getAmount() + oCartEntity.getAmount());
            return oCartRepository.save(oCartEntity).getId();
        } else {
            oCartEntity.setId(null);
            oCartEntity.setUser(oUserEntity);
            oCartEntity.setProduct(oProductEntity);
            return oCartRepository.save(oCartEntity).getId();
        }
    }

    // Update an existing cart
    public CartEntity update(CartEntity oCartEntity) {

        CartEntity oCartEntityFromDatabase = this.get(oCartEntity.getId());
        oCartEntity.setUser(oCartEntityFromDatabase.getUser());
        oCartEntity.setProduct(oCartEntityFromDatabase.getProduct());

        return oCartRepository.save(oCartEntity);
    }

    // Delete a cart by ID
    public Long delete(Long id) {
        oCartRepository.deleteById(id);
        return id;
    }

    // Delete all carts for a specific user
    public void deleteByUser(Long user_id) {
        oCartRepository.deleteByIdUser(user_id);
    }

    // Get all carts for a specific user
    public List<CartEntity> getAllByUser(Long user_id) {
        return oCartRepository.findAllByIdUser(user_id);
    }

    // Empty the cart table
    @Transactional
    public Long empty() {
        oCartRepository.deleteAll();
        oCartRepository.resetAutoIncrement();
        oCartRepository.flush();
        return oCartRepository.count();
    }

}