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
import com.ecommerce.urbanize.helper.CartDataGenerationHelper;
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

    @Autowired
    SessionService oSessionService;

    // Get cart by ID
    public CartEntity get(Long id) {
        return oCartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
    }

    // Get cart by user ID
    public Page<CartEntity> getCartByUser(Long user_id, Pageable oPageable) {
        oSessionService.onlyAdminsOrUsersWithTheirData(user_id);
        return oCartRepository.findByUserId(user_id, oPageable);
    }

    // Get cart by user ID and product ID
    public CartEntity getByUserAndProduct(Long user_id, Long product_id) {
        oSessionService.onlyAdminsOrUsersWithTheirData(user_id);
        return oCartRepository.findByUserIdAndProductId(user_id, product_id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
    }

    // Get page of carts
    public Page<CartEntity> getPage(Pageable oPageable) {
        oSessionService.onlyAdminsOrUsers();
        return oCartRepository.findAll(oPageable);
    }

    // Create a new cart
    public Long create(CartEntity oCartEntity) {
        oSessionService.onlyAdminsOrUsersWithTheirData(oSessionService.getSessionUser().getId());
        
        UserEntity oUserEntity = oSessionService.getSessionUser();

        ProductEntity oProductEntity = oProductService.get(oCartEntity.getProduct().getId());

        Optional<CartEntity> cartFromDatabase = oCartRepository.findByUserIdAndProductId(oUserEntity.getId(),
                oProductEntity.getId());
        if (cartFromDatabase.isPresent()) {
            CartEntity cart = cartFromDatabase.get();
            cart.setAmount(cart.getAmount() + oCartEntity.getAmount());
            oCartRepository.save(cart);
            return cart.getId();
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
        oSessionService.onlyAdminsOrUsersWithTheirData(oCartEntityFromDatabase.getUser().getId());
        oCartEntity.setUser(oCartEntityFromDatabase.getUser());
        oCartEntity.setProduct(oCartEntityFromDatabase.getProduct());

        return oCartRepository.save(oCartEntity);
    }

    // Delete a cart by ID
    public Long delete(Long id) {
        CartEntity oCartEntityFromDatabase = this.get(id);
        oSessionService.onlyAdminsOrUsersWithTheirData(oCartEntityFromDatabase.getUser().getId());
        if (oCartRepository.existsById(id)) {
            oCartRepository.deleteById(id);
            return id;
        } else {
            throw new ResourceNotFoundException("Error: El cart not found.");
        }
    }

    // Get all carts for a specific user
    public List<CartEntity> getAllByUser(Long user_id) {
        oSessionService.onlyAdminsOrUsersWithTheirData(user_id);
        return oCartRepository.findAllByIdUser(user_id);
    }

    // Populate database with random carts
    public Long populate(Integer amount) {
        oSessionService.onlyAdmins();
        for (int i = 0; i < amount; i++) {
            // Generate random cart data
            int amountInCart = CartDataGenerationHelper.getRandomAmount();
            // Get random user and product from repositories
            UserEntity randomUser = oUserService.getOneRandom();
            ProductEntity randomProduct = oProductService.getOneRandom();

            // Save the cart to the repository
            oCartRepository.save(new CartEntity(amountInCart, randomUser, randomProduct));
        }
        return oCartRepository.count();
    }

    // Calculate the cost of a specific cart
    public Double calculateCartCost(Long id) {
        return oCartRepository.calculateCartCost(id);
    }

    // Calculate the total cost of all carts for a specific user
    public Double calculateTotalCartCost(Long user_id) {
        return oCartRepository.calculateTotalCartCost(user_id);
    }

    // Remove all carts for a specific user
    @Transactional
    public void deleteByUserId(Long user_id) {
        oSessionService.onlyAdminsOrUsersWithTheirData(user_id);
        oCartRepository.deleteByUserId(user_id);
    }

    // Empty the cart table
    @Transactional
    public Long empty() {
        oSessionService.onlyAdmins();
        oCartRepository.deleteAll();
        oCartRepository.resetAutoIncrement();
        oCartRepository.flush();
        return oCartRepository.count();
    }

}